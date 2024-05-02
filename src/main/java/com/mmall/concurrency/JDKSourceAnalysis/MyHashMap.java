package com.mmall.concurrency.JDKSourceAnalysis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

/**
 * HashMap很多特色方法写在TreeNode里面
 *
 * @Author: lilingyan
 * @Date 2019/2/25 10:12
 */
public class MyHashMap<K, V>  {



	/***
	 * hash表
	 */
	transient Node<K, V>[] table;

	/**
	 * 初始hash表容量
	 * 默认16
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16  🐘⬅️移动4位10000=16

	/**
	 * HashMap整个容量的负载因子
	 * 默认0.75    大于容量的0.75倍就扩容
	 */
	final float loadFactor;
	/**
	 * 扩容阈值 threshold :The next size value at which to resize (capacity * load factor).
	 * 整个HashMap容量阈(yu)值
	 * 默认是loadFactor*hash表长 也可以loadFactor*用户自己传一个值
	 * 最终会变成一个最接近2^n的值(使用@tableSizeFor()方法计算的)
	 * TODO 是HashMap所能容纳的最大node节点的个数 threshold = 容量*负载因子  负载因子默认0.75
	 */
	int threshold;

	/**
	 * 链表节点转换红黑树节点的阈值, 8个节点转
	 */
	static final int TREEIFY_THRESHOLD = 8;

	/**
	 * 红黑树节点转换链表节点的阈值, 6个节点转
	 */
	static final int UNTREEIFY_THRESHOLD = 6;

	/**
	 * 转红黑树时, table的最小长度
	 */
	static final int MIN_TREEIFY_CAPACITY = 64;//当哈希表中的所有元素个数超过64时，才会允许树化

	/**
	 * 1<<30 表示1左移30位，每左移一位乘以2，所以就是1*2^30=1073741824
	 * 最大初始hash表容量，2^30
	 * 因为int只有4字节32位，要表示正负，所以正数才31位
	 * 所以如果超过 2^30的数，是不能乘以2了
	 */
	static final int MAXIMUM_CAPACITY = 1 << 30;

	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * 节点数量
	 * size属性指的就是键值总数
	 */
	transient int size;

	//=========================构造器==========================
	public MyHashMap() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}

	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	public MyHashMap(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal initial capacity: " +
					initialCapacity);
		if (initialCapacity > MAXIMUM_CAPACITY)
			initialCapacity = MAXIMUM_CAPACITY;
		if (loadFactor <= 0 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException("Illegal load factor: " +
					loadFactor);
		this.loadFactor = loadFactor;
		//tableSizeFor(initialCapacity)含义:最接近的2^n数  initialCapacity>=2^n
		this.threshold = tableSizeFor(initialCapacity);
	}
	//=========================构造器==========================

	//=========================添加==========================
	public V put(K key, V value) {
		// TODO key变成hash(key)
		return putVal(hash(key), key, value, false, true);
	}

	final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
	               boolean evict) {
		//tab：引用当前hashMap的散列表
		//p：表示当前散列表的元素
		//n：表示散列表数组的长度
		//i：表示路由寻址 结果
		Node<K, V>[] tab;
		Node<K, V> p;
		int n, i;
		if ((tab = table) == null || (n = tab.length) == 0) {
			//延时初始化 第一次插入数据是再进行初始化化 resize()进行初始化数组
			n = (tab = resize()).length;//n：表示散列表数组的长度
		}
		//假设n=16 , 那么key的hash值只有后四位参与的运算 所以让key的高十六位和低十六位进行异或运算可以让节点node更均匀的分配(tab[])
		/**
		 * 如果没有hash碰撞则会直接插入元素。如果线程A和线程B同时进行put操作，刚好这两条不同的数据hash值一样，并且该位置数据为null，
		 * 所以这线程A、B都会进入第6行代码中。假设一种情况，线程A进入后还未进行数据插入时挂起，而线程B正常执行，从而正常插入数据，
		 * 然后线程A获取CPU时间片，此时线程A不用再进行hash判断了，问题出现：线程A会把线程B插入的数据给覆盖，发生线程不安全
		 */
		//TODO (n - 1) & hash = 1111&hash  hash值的后四位决定落在那个槽里
		//TODO 多个线程同时走到,再往下执行,就会覆盖上一个操作添加的元素, 造成元素丢失
		//TODO 刚好这两条不同的数据 (n - 1) & hash值一样(即hash值的后四位相同)，并且该位置数据为null
		if ((p = tab[i = (n - 1) & hash]) == null)// 如果没有hash碰撞则直接插入元素 TODO 此处线程不安全 (key.hashCode()^key.hashCode()>>>16) & (n - 1) 解释起来就是 key.hashCode值进行高16位与低16位进行异或运算 再与 n-1进行与运算
			//如果表当前桶没有节点，则直接添加(第一个)
			tab[i] = newNode(hash, key, value, null);
		else {
			//e：不为null的话，找到了一个与当前要插入的key-value相同key的元素
			//k：表示临时的一个key
			Node<K, V> e;
			K k;
			if (p.hash == hash &&
					((k = p.key) == key || (key != null && key.equals(k))))
				//当前元素p与插入元素的key相同 并且hash(key)也相同 则 直接覆盖当前元素p
				e = p;
			else if (p instanceof TreeNode)//当前元素p的结构是红黑树
				//如果是红黑树，则查找是否已有相同的key
				e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
			else {
				//普通链表节点 尾插法
				for (int binCount = 0; ; ++binCount) {//计数 binCount
					if ((e = p.next) == null) {//TODO 多个线程同时走到,再往下执行,就会覆盖上一个操作添加的元素, 造成元素丢失
						//p.next == null 说明已经到最后一个节点了
						p.next = newNode(hash, key, value, null);
						/**
						 * 判断是否超过链表转红黑树阈值
						 * 链表长度大于等于8个node则转换为红黑树 前提是  table的最小长度大于64时才转换成红黑树否则扩容
						 * 7个元素 再加入newNode(hash, key, value, null); 正好8个元素
						 */
						if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
							// 链表长度等于8时,链表转化成红黑树 table的最小长度小于64时 进行扩容 不转红黑树
							treeifyBin(tab, hash);
						break;
					}
					if (e.hash == hash &&
							((k = e.key) == key || (key != null && key.equals(k))))
						//如果相等，则break 结束循环
						break;
					//继续往下执行
					p = e;//p = p.next 一直遍历 直到break
				}
			}
			/**
			 * e=p的key与要插入的key一样 内存地址一样 内容也一样
			 * 在这里做覆盖操作
			 * 并返回旧值
			 * onlyIfAbsent 默认是false return putVal(hash(key), key, value, onlyIfAbsent -> false, true);
			 */
			//e不等于null，条件成立说明，找到了一个与你插入元素key完全一致的数据，需要进行替换
			if (e != null) { // existing mapping for key
				V oldValue = e.value;
				if (!onlyIfAbsent || oldValue == null)
					e.value = value;
//                afterNodeAccess(e);
				return oldValue;
			}
		}
		// 是否大于12?
		//如果是旧值覆盖已经return oldValue了
		//插入新元素，size自增，如果自增后的值大于扩容阈值，则触发扩容。
		if (++size > threshold) //size属性指的就是键值总数 大于扩容阈值 没有等于
			resize();
//        afterNodeInsertion(evict);
		return null;
	}
	//=========================添加==========================

	//=========================特色方法==========================

	/**
	 * 添加元素后
	 * 如果超过链表的长度限制阈值
	 * 则扩容或者转红黑树
	 *
	 * @param tab
	 * @param hash
	 */
	final void treeifyBin(Node<K, V>[] tab, int hash) {
		int n, index;
		Node<K, V> e;
		/**
		 * 如果hash表还没到设置的最小长度（MIN_TREEIFY_CAPACITY）
		 * 则先扩展hash表
		 */
		if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)//小于64 没有等于
			resize();
		else if ((e = tab[index = (n - 1) & hash]) != null) {
			//作用在增加数据的桶
			TreeNode<K, V> hd = null, tl = null; //hd 头节点
			do {
				/**
				 * 倒转了链表
				 * 并变成了双向链表
				 */
				TreeNode<K, V> p = replacementTreeNode(e, null); //普通单项链表节点变成树节点
				if (tl == null)
					hd = p;
				else {
					p.prev = tl;
					tl.next = p;
				}
				tl = p;
			} while ((e = e.next) != null);
			if ((tab[index] = hd) != null)  //新双向链表替换旧单向链表
				hd.treeify(tab);    //构建红黑树
		}
	}

	/**
	 * 重新计算hash表长度
	 * resize() 假设原来的table长度是16 则扩容后长度是32
	 * 则参加位于运算的tab[i = (n - 1) & hash]) 由原来的(16-1)=1111变成了(32-1)=11111增加了1bit
	 * 可以认为新增加的1bit是随机的 可以均匀的把之前发生hash碰撞的节点分散到两个bucket里
	 *
	 * @return
	 */
	final Node<K, V>[] resize() {
		//需要扩容的老table
		Node<K, V>[] oldTab = table;
		//老的table[]数组的长度
		int oldCap = (oldTab == null) ? 0 : oldTab.length;
		//老的最大容量大小 The next size value at which to resize (capacity * load factor).
		int oldThr = threshold;//表示扩容之前的扩容阈值，触发本次扩容的阈值
		//新的table[]数组的长度和新的最大容量大小
		//newCap：扩容之后table数组的大小
		//newThr：扩容之后，下次再次触发扩容的条件
		int newCap, newThr = 0; // newCap--oldCap   oldThr--newThr
		//说明已经初始化过了table[]
		if (oldCap > 0) {
			if (oldCap >= MAXIMUM_CAPACITY) {
				/**
				 * 超过int的最大值(MAXIMUM_CAPACITY = 1 << 30)就不再扩容了 只好随它去碰撞
				 * 如果的hash表已经超过了2^30次
				 * 就不能再计算@tableSizeFor()方法了(超过int上限了)
				 * 直接用int最大值
				 */
				threshold = Integer.MAX_VALUE;
				return oldTab;

			} else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
					oldCap >= DEFAULT_INITIAL_CAPACITY)// oldCap << 1 左移一位 代表扩容翻一倍
			/**
			 *
			 * newCap容量扩大两倍
			 * 如果表容量没超过最大阈值 并且 第一次就设置过大于默认最小hash表长或者已经扩容过了
			 * 则把容量阈值也扩大两倍
			 */
				newThr = oldThr << 1; // double threshold
			//第一次进行初始化操作 一种是调用有参的构造函数 一种是调用无参的构造函数进行初始化
		} else if (oldThr > 0) // oldCap==0 & oldThr > 0 调用有参的构造函数  设置了初始化容量的大小 	int oldThr = threshold;
			//调用了有参的构造函数，指定了大小 这个地方是将初始化容量赋值给了threshold
			// 此时的threshold不是个容纳的最大节点数量 而是初始化容量 后面会对该值进行修正
			newCap = oldThr;
		else {               // zero initial threshold signifies using defaults
			//无参构造 并且 第一次调用resize()的时候 初始化一下参数
			newCap = DEFAULT_INITIAL_CAPACITY;
			newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
		}
		if (newThr == 0) {
			/**
			 * 1.初始化的时候指定了容量大小 这里要对threshold做一下修正 因为初始化的时候threshold的值是不正确的
			 * 如果构造的时候指定的大小有问题
			 * 这里修正一下
			 * 2.如果newCap = oldCap << 1大于等于MAXIMUM_CAPACITY
			 *  这里在if判断时已经把oldCap赋值给newCap了
			 */
			float ft = (float) newCap * loadFactor;//新的扩容阈值
			newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
					(int) ft : Integer.MAX_VALUE);
		}
		threshold = newThr;
		@SuppressWarnings({"rawtypes", "unchecked"})
		Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];// TODO
		//如果是第一次扩容到这里就结束了
		table = newTab;// TODO 此时实例变量table是空的 如果此时另一个线程执行get时，就会get出null
		if (oldTab != null) { //说明，hashMap本次扩容之前，table不为null
			/**
			 * 遍历老hash表
			 */
			for (int j = 0; j < oldCap; ++j) {
				Node<K, V> e;// e当前node节点
				if ((e = oldTab[j]) != null) { //说明当前桶位中有数据，但是数据具体是 单个数据，还是链表 还是 红黑树 并不知道
					oldTab[j] = null; // for GC
					if (e.next == null)
						//第一种情况：当前桶位只有一个元素，从未发生过碰撞，这情况 直接计算出当前元素应存放在 新数组中的位置，然后扔进去就可以了
						newTab[e.hash & (newCap - 1)] = e;
					else if (e instanceof TreeNode)
					/**
					 * 如果已经是红黑树了
					 * 与下面的链表处理同理(不过多了一个如果红黑树过短，拆成链表的操作)
					 */
						((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
					else { // e.next != null 第三种情况：桶位已经形成链表
						/**
						 * 一句话总结就是:判断新增加的1bit是0还是1 0还在原来的位置 1放到新的位置table[原来的位置+老容量]
						 * 原桶中的链表转移到新桶中链表时
						 * 只可能出现两种情况
						 * 还是在原来的位置上，或者偏移一个原本hash表长度的位置
						 * （
						 *  ep:
						 *      1-32 & 16
						 *      0~0(16个) 16~16(16个)
						 * ）
						 *
						 * 所以这里纪录两个链表(高位和低位)
						 * 把原来的链表拆到这两个新链表里面
						 * 最后把这两个新链表挂到新hash表上
						 */
						Node<K, V> loHead = null, loTail = null;  //低位链表：存放在扩容之后的数组的下标位置，与当前数组的下标位置一致。
						Node<K, V> hiHead = null, hiTail = null;  //高位链表：存放在扩容之后的数组的下表位置为 当前数组下标位置 + 扩容之前数组的长度  index=15=>index=31
						Node<K, V> next;
						do {
							next = e.next;
							//判断新增加的1bit是0还是1 0还在原来的位置
							if ((e.hash & oldCap) == 0) { // 以前确定位置是 e.hash&(n-1) -> e.hash&(oldCap-1)
								if (loTail == null) //是0放到新的位置table[原来的位置]
									loHead = e;
								else
									loTail.next = e;
								loTail = e;
								//判断新增加的1bit是0还是1 1放到新的位置table[原来的位置+老容量]
							} else {
								if (hiTail == null)
									hiHead = e;
								else
									hiTail.next = e;
								hiTail = e;
							}
						} while ((e = next) != null);
						// 采用头插法
						if (loTail != null) {
							loTail.next = null;
							newTab[j] = loHead;// 存放头节点 是0放到新的位置table[原来的位置]
						}
						if (hiTail != null) {
							hiTail.next = null;
							newTab[j + oldCap] = hiHead;//存放头节点 1放到新的位置table[原来的位置+老容量]
						}
					}
				}
			}
		}
		return newTab;
	}



	//=========================特色方法==========================

	//=========================删除==========================
	public V remove(Object key) {
		Node<K, V> e;
		return (e = removeNode(hash(key), key, null, false, true)) == null ?
				null : e.value;
	}

	/**
	 * @param hash
	 * @param key
	 * @param value      要删除的值
	 * @param matchValue 如果true，则要key和value都相等才删除
	 * @param movable
	 * @return
	 */
	final Node<K, V> removeNode(int hash, Object key, Object value,
	                            boolean matchValue, boolean movable) {
		Node<K, V>[] tab;
		Node<K, V> p;
		int n, index;
		if ((tab = table) != null && (n = tab.length) > 0 &&
				(p = tab[index = (n - 1) & hash]) != null) {
			//如果桶中有这个hash
			Node<K, V> node = null, e;
			K k;
			V v;
			if (p.hash == hash &&
					((k = p.key) == key || (key != null && key.equals(k))))
				//桶中节点就是要删除的节点
				node = p;
			else if ((e = p.next) != null) {
				if (p instanceof TreeNode)
					//在红黑树查找
					node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
				else {
					//在链表中查找
					do {
						if (e.hash == hash &&
								((k = e.key) == key ||
										(key != null && key.equals(k)))) {
							node = e;
							break;
						}
						p = e;
					} while ((e = e.next) != null);
				}
			}
			if (node != null && (!matchValue || (v = node.value) == value ||
					(value != null && value.equals(v)))) {
				if (node instanceof TreeNode)
					//如果是红黑树删除
					((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);
				else if (node == p)
					//如果删除的是桶中的节点   hash表指针直接跳过他就行
					tab[index] = node.next;
				else    //链表的话 也直接跳过就行
					p.next = node.next;
				--size;
//                    afterNodeRemoval(node);
				return node;
			}
		}
		return null;
	}
	//=========================删除==========================

	//=========================查找==========================
	public V get(Object key) {
		Node<K, V> e;
		return (e = getNode(hash(key), key)) == null ? null : e.value;
	}

	final Node<K, V> getNode(int hash, Object key) {
		//tab：引用当前hashMap的散列表
		//first：桶位中的头元素
		//e：临时node元素
		//n：table数组长度
		Node<K, V>[] tab;
		Node<K, V> first, e;
		int n;
		K k;
		if ((tab = table) != null && (n = tab.length) > 0 &&
				(first = tab[(n - 1) & hash]) != null) {
			//如果hash表不是空，并且当前桶位置有节点
			if (first.hash == hash && // always check first node
					((k = first.key) == key || (key != null && key.equals(k))))
				//如果key的hash相等，并且 key的地址或者值相等  就返回这个节点(所以可以用null做key)
				return first;
			if ((e = first.next) != null) {
				if (first instanceof TreeNode)
					//如果是红黑树，则直接调用红黑树的查询方法
					return ((TreeNode<K, V>) first).getTreeNode(hash, key);
				do {
					/**
					 * 如果是链表
					 * 则一直循环到底
					 * 查一样的
					 */
					if (e.hash == hash &&
							((k = e.key) == key || (key != null && key.equals(k))))
						return e;
				} while ((e = e.next) != null);
			}
		}
		return null;
	}

	public boolean containsKey(Object key) {
		return getNode(hash(key), key) != null;
	}

	public int size() {
		return size;
	}
	//=========================查找==========================

	//=========================一些常用方法封装==========================

	/**
	 * 由于桶长一般不会太长(@DEFAULT_INITIAL_CAPACITY默认16 =》BIN 10000)
	 * key被hash分桶时，只会被用到低几位
	 * 为了增加平衡的概率，把低位和高位做了异或(使key更均匀分散在桶中)
	 * java int4个字节，32位长，所以向右移16位，就是高位中的低位
	 * 更加均匀的分布到数组中
	 */
	/**
	 *TODO 如果key不为空，并且取到k的hash值为：
	 * h1 = 0b 0010 0101 1010 1100 0011 1111 0010 1110  将它无符号右移16位后做异或运算
	 * ^
	 * h2 = 0b 0000 0000 0000 0000 0010 0101 1010 1100
	 *   => 0b 0010 0101 1010 1100 0001 1010 1000 0010
	 *   主要目的就是为了让hash值更加分散不重复 防止hash冲突
	 *
	 *
	 */
	static final int hash(Object key) {
		int h;
		//TODO 异或^相同为0不同为1无符号右移动16位hashcode计算的值有可能是负数所以选择的是无符号右移 >>>16
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	/**
	 * 返回大于且最接近当前值的2^n数
	 * 计算hash表的长度
	 *  cap =10
	 *  n = cap-1 = 9
	    n |= n >>> 1; 无符号右移一位1001前面加1个0后面舍去最后1位得0b0100
	    0b1001 | 0b0100 => 0b1101
	    n |= n >>> 2;无符号右二位1101前面加2个0后面舍去最后2位得0b0100
	    0b1101 | 0b0011 => 0b1111
	    n |= n >>> 4;
	    0b1111 | 0b0000 => 0b1111
	    n |= n >>> 8;
	    0b1111 | 0b0000 => 0b1111
	     n |= n >>> 16;
	    0b1111 | 0b0000 => 0b1111
	     你会发现到最后其实都是一样的了。
	     那么n=0b1111 转换为10进制为：
	     (1*2的0次方=1)+(1*2的1次方=2)+(1*2的2次方=4)+(1*2的3次方=8)=1+2+4+8=15
	     (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	     n=15不小于0，并且不大于容量的最大值，则n=n+1=16。
	 *
	 */
	//TODO cap =10 n = cap-1 = 9 (1001)  这个计算的目的就是 把 1001变成1111 -> 即大于或等于cap的最小2的幂
	//第一步 int n = cap - 1; 这个操作，执行这个操作的主要原因是为了防止在cap已经是2的n次幂的情况下，
	//经过运算后得到的结果是cap的二倍的结果
	static final int tableSizeFor(int cap) {//找到大于或等于cap的最小2的幂
		int n = cap - 1;
		n |= n >>> 1; //右移一位
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	/**
	 * @param x
	 * @return Comparable中的比较类型
	 */
	static Class<?> comparableClassFor(Object x) {
		if (x instanceof Comparable) {
			Class<?> c;
			Type[] ts, as;
			Type t;
			ParameterizedType p;
			if ((c = x.getClass()) == String.class) // bypass checks
				return c;
			if ((ts = c.getGenericInterfaces()) != null) {
				for (int i = 0; i < ts.length; ++i) {
					if (((t = ts[i]) instanceof ParameterizedType) &&
							((p = (ParameterizedType) t).getRawType() ==
									Comparable.class) &&
							(as = p.getActualTypeArguments()) != null &&
							as.length == 1 && as[0] == c) // type arg is c
						return c;
				}
			}
		}
		return null;
	}

	/**
	 * @param kc
	 * @param k  要查找的key
	 * @param x  当前的key
	 * @return
	 */
	static int compareComparables(Class<?> kc, Object k, Object x) {
		return (x == null || x.getClass() != kc ? 0 :
				((Comparable) k).compareTo(x));
	}

	Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next) {
		return new Node<>(p.hash, p.key, p.value, next);
	}

	TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next) {
		return new TreeNode<>(hash, key, value, next);
	}

	Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
		return new Node<>(hash, key, value, next);
	}

	TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next) {
		return new TreeNode<>(p.hash, p.key, p.value, next);
	}
	//=========================一些常用方法封装==========================

	//=========================使用到的节点结构==========================

	/**
	 * 普通的单链表节点
	 *
	 * @param <K>
	 * @param <V>
	 */
	static class Node<K, V> implements Map.Entry<K, V> {
		final int hash;
		final K key;
		V value;
		Node<K, V> next;

		Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return value;
		}

		public final String toString() {
			return key + "=" + value;
		}

		public final int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}

		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		public final boolean equals(Object o) {
			if (o == this)
				return true;
			if (o instanceof Map.Entry) {
				Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
				if (Objects.equals(key, e.getKey()) &&
						Objects.equals(value, e.getValue()))
					return true;
			}
			return false;
		}
	}

	/**
	 * HashMap中特有的树节点
	 * 它在数节点结构上还支持双向链表
	 *
	 * @param <K>
	 * @param <V>
	 */
	static final class TreeNode<K, V> extends Node<K, V> {
		TreeNode<K, V> parent;  // red-black tree links
		TreeNode<K, V> left;
		TreeNode<K, V> right;
		TreeNode<K, V> prev;    // needed to unlink next upon deletion
		boolean red;

		TreeNode(int hash, K key, V val, Node<K, V> next) {
			super(hash, key, val, next);
		}

		final TreeNode<K, V> root() {
			for (TreeNode<K, V> r = this, p; ; ) {
				if ((p = r.parent) == null)
					return r;
				r = p;
			}
		}

		/**
		 * 红黑树的root节点移动到链表的第一个（hash桶中）
		 *
		 * @param tab
		 * @param root
		 * @param <K>
		 * @param <V>
		 */
		static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
			int n;
			if (root != null && tab != null && (n = tab.length) > 0) {
				/**
				 * 获取root的hash值，然后获取该hash的桶
				 * 比较root是否是hash桶的元素(第一个)
				 */
				int index = (n - 1) & root.hash;
				TreeNode<K, V> first = (TreeNode<K, V>) tab[index];
				/**
				 * 如果当前root节点不是hash桶中的节点
				 * 则从双向链表中取出root节点(root的节点的前后节点互指)
				 * 把root节点放在hash桶中
				 * 最后把root节点和原来在桶中的节点前后互指
				 */
				if (root != first) {
					Node<K, V> rn;
					tab[index] = root;
					TreeNode<K, V> rp = root.prev;
					if ((rn = root.next) != null)
						((TreeNode<K, V>) rn).prev = rp;
					if (rp != null)
						rp.next = rn;
					if (first != null)
						first.prev = root;
					root.next = first;
					root.prev = null;
				}
				assert checkInvariants(root);
			}
		}

		/**
		 * @param h
		 * @param k
		 * @param kc key的class 类型
		 * @return
		 */
		final TreeNode<K, V> find(int h, Object k, Class<?> kc) {
			TreeNode<K, V> p = this;
			do {
				/**
				 * ph   p的hash
				 * pl   p的key
				 * dir  key的比较大小
				 */
				int ph, dir;
				K pk;
				TreeNode<K, V> pl = p.left, pr = p.right, q;
				//先判断hash大小
				if ((ph = p.hash) > h)
					p = pl;
				else if (ph < h)
					p = pr;
				else if ((pk = p.key) == k || (k != null && k.equals(pk)))
					//如果hash相等，则再确认下是否真相等(hash可能重复)
					return p;
				/**
				 * 如果只有右子树或者左子树，那必定在那个树里
				 * 如果有两个子树，则用key去比较(key需要实现Comparable接口)
				 * 如果key不能比较大小
				 * 最后只能先遍历有子树，如果没有，再去遍历左子树
				 */
				else if (pl == null)
					p = pr;
				else if (pr == null)
					p = pl;
				else if ((kc != null ||
						(kc = comparableClassFor(k)) != null) &&
						(dir = compareComparables(kc, k, pk)) != 0)
					p = (dir < 0) ? pl : pr;
				else if ((q = pr.find(h, k, kc)) != null)
					return q;
				else
					p = pl;
			} while (p != null);
			return null;
		}

		/**
		 * @param h 要查找的key的hash
		 * @param k 要查找的key
		 * @return
		 */
		final TreeNode<K, V> getTreeNode(int h, Object k) {
			return ((parent != null) ? root() : this).find(h, k, null);
		}

		static int tieBreakOrder(Object a, Object b) {
			int d;
			if (a == null || b == null ||
					(d = a.getClass().getName().
							compareTo(b.getClass().getName())) == 0)
				d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
						-1 : 1);
			return d;
		}

		/**
		 * 构建红黑树
		 *
		 * @param tab
		 */
		final void treeify(Node<K, V>[] tab) {
			TreeNode<K, V> root = null;
			/**
			 * 遍历链表
			 * 生成一颗红黑树(经过平衡后，当前节点虽然是桶的节点，但不一定是树的根节点)
			 */
			for (TreeNode<K, V> x = this, next; x != null; x = next) {
				next = (TreeNode<K, V>) x.next;
				x.left = x.right = null;
				if (root == null) {
					x.parent = null;
					x.red = false;
					root = x;
				} else {
					K k = x.key;
					int h = x.hash;
					Class<?> kc = null;
					for (TreeNode<K, V> p = root; ; ) {
						int dir, ph;
						K pk = p.key;
						if ((ph = p.hash) > h)
							dir = -1;
						else if (ph < h)
							dir = 1;
						else if ((kc == null &&
								(kc = comparableClassFor(k)) == null) ||
								(dir = compareComparables(kc, k, pk)) == 0)
						/**
						 * 链表转红黑树
						 * 正常来说 应该不会执行到相等的情况 ？
						 */
							dir = tieBreakOrder(k, pk);

						TreeNode<K, V> xp = p;
						if ((p = (dir <= 0) ? p.left : p.right) == null) {
							x.parent = xp;
							if (dir <= 0)
								xp.left = x;
							else
								xp.right = x;
							root = balanceInsertion(root, x);
							break;
						}
					}
				}
			}
			//把红黑树的root节点放置在桶中
			moveRootToFront(tab, root);
		}

		/**
		 * 红黑树转链表节点
		 *
		 * @param map
		 * @return
		 */
		final Node<K, V> untreeify(MyHashMap<K, V> map) {
			Node<K, V> hd = null, tl = null;
			/**
			 * HashMap的红黑树也是双向链表
			 * 把所有树节点遍历，然后变成单链表返回
			 */
			for (Node<K, V> q = this; q != null; q = q.next) {
				Node<K, V> p = map.replacementNode(q, null);
				if (tl == null)
					hd = p;
				else
					tl.next = p;
				tl = p;
			}
			return hd;
		}

		/**
		 * 添加红黑树节点
		 *
		 * @param map 当前hashmap
		 * @param tab hash表
		 * @param h   插入的hash
		 * @param k   插入的key
		 * @param v   插入的值
		 * @return
		 */
		final TreeNode<K, V> putTreeVal(MyHashMap<K, V> map, Node<K, V>[] tab,
		                                int h, K k, V v) {
			Class<?> kc = null;
			/**
			 * 一颗树里
			 * 有多个hash重复的可能
			 * 因为是递归查找的(一直到低)
			 * 所以只要父节点已经查找过了
			 * 子节点就没必要再查找了
			 */
			boolean searched = false;
			/**
			 * 这个this必定是root节点
			 * 不知道为什么要取一下root()
			 */
			TreeNode<K, V> root = (parent != null) ? root() : this;
			/**
			 * 普通二叉树的查询
			 */
			for (TreeNode<K, V> p = root; ; ) {
				int dir, ph;
				K pk;
				if ((ph = p.hash) > h)
					dir = -1;
				else if (ph < h)
					dir = 1;
				else if ((pk = p.key) == k || (k != null && k.equals(pk)))
					return p;
				else if ((kc == null &&
						(kc = comparableClassFor(k)) == null) ||
						(dir = compareComparables(kc, k, pk)) == 0) {
					/**
					 * 如果添加的节点hash值与已存在的有重复
					 * 则递归查询重复节点左右子树
					 * 是否有key和插入节点相等的(实现Comparable接口)
					 */
					if (!searched) {
						TreeNode<K, V> q, ch;
						searched = true;
						if (((ch = p.left) != null &&
								(q = ch.find(h, k, kc)) != null) ||
								((ch = p.right) != null &&
										(q = ch.find(h, k, kc)) != null))
							return q;
					}
					dir = tieBreakOrder(k, pk);
				}

				TreeNode<K, V> xp = p;
				/**
				 * 把p指向下层节点继续递归(如果还有)
				 * 如果没有了，说明添加的节点在树中没有
				 * 则加一个新的叶子节点，然后返回
				 */
				if ((p = (dir <= 0) ? p.left : p.right) == null) {
					Node<K, V> xpn = xp.next;
					TreeNode<K, V> x = map.newTreeNode(h, k, v, xpn);
					if (dir <= 0)
						xp.left = x;
					else
						xp.right = x;
					xp.next = x;
					x.parent = x.prev = xp;
					if (xpn != null)
						((TreeNode<K, V>) xpn).prev = x;
					moveRootToFront(tab, balanceInsertion(root, x));
					return null;
				}
			}
		}

		final void removeTreeNode(MyHashMap<K, V> map, Node<K, V>[] tab,
		                          boolean movable) {
			int n;
			if (tab == null || (n = tab.length) == 0)
				return;
			int index = (n - 1) & hash; //获取所在桶坐标
			TreeNode<K, V> first = (TreeNode<K, V>) tab[index], root = first, rl;
			TreeNode<K, V> succ = (TreeNode<K, V>) next, pred = prev;  //后继 前驱 节点
			if (pred == null)
				//如果前驱节点等于空 说明当前节点就是桶中的节点，则直接后继节点替换掉桶中的节点
				tab[index] = first = succ;
			else
				pred.next = succ;   //如果前驱节点不等于空 让前驱节点的后指针指向当前节点的后面(跳过当前节点)
			if (succ != null)
				//如果后继节点不等于空 让后继节点的前指针指向当前节点的前面(跳过当前节点) 与上步一起，跳过了当前节点(等于删除)
				succ.prev = pred;
			if (first == null)  //如果删掉的节点没有子树了，就不需要后面的操作
				return;
			if (root.parent != null)    //如果删掉的是原来的root节点，则需要重新定位一下root节点
				root = root.root();
			if (root == null || root.right == null ||
					(rl = root.left) == null || rl.left == null) {
				/**
				 * 因为红黑树定义为 左右差最大时2倍
				 * 所以如果左右子树有一个是空    或者左子树的左子树是空
				 * 那么这颗树不可能超过UNTREEIFY_THRESHOLD(6)
				 *
				 * 如果是，那就把红黑树专成单链表
				 */
				tab[index] = first.untreeify(map);  // too small
				return;
			}
			/**
			 * 普通的红黑树节点删除
			 */
			TreeNode<K, V> p = this, pl = left, pr = right, replacement;
			if (pl != null && pr != null) {
				TreeNode<K, V> s = pr, sl;
				while ((sl = s.left) != null) // find successor
					s = sl;
				boolean c = s.red;
				s.red = p.red;
				p.red = c; // swap colors
				TreeNode<K, V> sr = s.right;
				TreeNode<K, V> pp = p.parent;
				if (s == pr) { // p was s's direct parent
					p.parent = s;
					s.right = p;
				} else {
					TreeNode<K, V> sp = s.parent;
					if ((p.parent = sp) != null) {
						if (s == sp.left)
							sp.left = p;
						else
							sp.right = p;
					}
					if ((s.right = pr) != null)
						pr.parent = s;
				}
				p.left = null;
				if ((p.right = sr) != null)
					sr.parent = p;
				if ((s.left = pl) != null)
					pl.parent = s;
				if ((s.parent = pp) == null)
					root = s;
				else if (p == pp.left)
					pp.left = s;
				else
					pp.right = s;
				if (sr != null)
					replacement = sr;
				else
					replacement = p;
			} else if (pl != null)
				replacement = pl;
			else if (pr != null)
				replacement = pr;
			else
				replacement = p;
			if (replacement != p) {
				TreeNode<K, V> pp = replacement.parent = p.parent;
				if (pp == null)
					root = replacement;
				else if (p == pp.left)
					pp.left = replacement;
				else
					pp.right = replacement;
				p.left = p.right = p.parent = null;
			}

			TreeNode<K, V> r = p.red ? root : balanceDeletion(root, replacement);

			if (replacement == p) {  // detach
				TreeNode<K, V> pp = p.parent;
				p.parent = null;
				if (pp != null) {
					if (p == pp.left)
						pp.left = null;
					else if (p == pp.right)
						pp.right = null;
				}
			}
			if (movable)
				moveRootToFront(tab, r);
		}

		/**
		 * 与resize()同理
		 *
		 * @param map   整个HashMap
		 * @param tab   新的hash表
		 * @param index 当前旧hash表的位置
		 * @param bit   旧hash表的长度
		 */
		final void split(MyHashMap<K, V> map, Node<K, V>[] tab, int index, int bit) {
			TreeNode<K, V> b = this;
			// Relink into lo and hi lists, preserving order
			TreeNode<K, V> loHead = null, loTail = null;
			TreeNode<K, V> hiHead = null, hiTail = null;
			int lc = 0, hc = 0;
			for (TreeNode<K, V> e = b, next; e != null; e = next) {
				next = (TreeNode<K, V>) e.next;
				e.next = null;
				if ((e.hash & bit) == 0) {
					if ((e.prev = loTail) == null)
						loHead = e;
					else
						loTail.next = e;
					loTail = e;
					++lc;
				} else {
					if ((e.prev = hiTail) == null)
						hiHead = e;
					else
						hiTail.next = e;
					hiTail = e;
					++hc;
				}
			}

			if (loHead != null) {
				if (lc <= UNTREEIFY_THRESHOLD)
				/**
				 * 这里比 resize() 处理链表多一个操作
				 * 就是如果红黑树少于设定的阈值了
				 * 把它转换成链表
				 */
					tab[index] = loHead.untreeify(map);
				else {
					tab[index] = loHead;
					if (hiHead != null) // (else is already treeified)
						loHead.treeify(tab);
				}
			}
			if (hiHead != null) {
				if (hc <= UNTREEIFY_THRESHOLD)
					tab[index + bit] = hiHead.untreeify(map);
				else {
					tab[index + bit] = hiHead;
					if (loHead != null)
						hiHead.treeify(tab);
				}
			}
		}

		/* ------------------------------------------------------------ */
		// Red-black tree methods, all adapted from CLR

		static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root,
		                                        TreeNode<K, V> p) {
			TreeNode<K, V> r, pp, rl;
			if (p != null && (r = p.right) != null) {
				if ((rl = p.right = r.left) != null)
					rl.parent = p;
				if ((pp = r.parent = p.parent) == null)
					(root = r).red = false;
				else if (pp.left == p)
					pp.left = r;
				else
					pp.right = r;
				r.left = p;
				p.parent = r;
			}
			return root;
		}

		static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root,
		                                         TreeNode<K, V> p) {
			TreeNode<K, V> l, pp, lr;
			if (p != null && (l = p.left) != null) {
				if ((lr = p.left = l.right) != null)
					lr.parent = p;
				if ((pp = l.parent = p.parent) == null)
					(root = l).red = false;
				else if (pp.right == p)
					pp.right = l;
				else
					pp.left = l;
				l.right = p;
				p.parent = l;
			}
			return root;
		}

		static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root,
		                                              TreeNode<K, V> x) {
			x.red = true;
			for (TreeNode<K, V> xp, xpp, xppl, xppr; ; ) {
				if ((xp = x.parent) == null) {
					x.red = false;
					return x;
				} else if (!xp.red || (xpp = xp.parent) == null)
					return root;
				if (xp == (xppl = xpp.left)) {
					if ((xppr = xpp.right) != null && xppr.red) {
						xppr.red = false;
						xp.red = false;
						xpp.red = true;
						x = xpp;
					} else {
						if (x == xp.right) {
							root = rotateLeft(root, x = xp);
							xpp = (xp = x.parent) == null ? null : xp.parent;
						}
						if (xp != null) {
							xp.red = false;
							if (xpp != null) {
								xpp.red = true;
								root = rotateRight(root, xpp);
							}
						}
					}
				} else {
					if (xppl != null && xppl.red) {
						xppl.red = false;
						xp.red = false;
						xpp.red = true;
						x = xpp;
					} else {
						if (x == xp.left) {
							root = rotateRight(root, x = xp);
							xpp = (xp = x.parent) == null ? null : xp.parent;
						}
						if (xp != null) {
							xp.red = false;
							if (xpp != null) {
								xpp.red = true;
								root = rotateLeft(root, xpp);
							}
						}
					}
				}
			}
		}

		static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root,
		                                             TreeNode<K, V> x) {
			for (TreeNode<K, V> xp, xpl, xpr; ; ) {
				if (x == null || x == root)
					return root;
				else if ((xp = x.parent) == null) {
					x.red = false;
					return x;
				} else if (x.red) {
					x.red = false;
					return root;
				} else if ((xpl = xp.left) == x) {
					if ((xpr = xp.right) != null && xpr.red) {
						xpr.red = false;
						xp.red = true;
						root = rotateLeft(root, xp);
						xpr = (xp = x.parent) == null ? null : xp.right;
					}
					if (xpr == null)
						x = xp;
					else {
						TreeNode<K, V> sl = xpr.left, sr = xpr.right;
						if ((sr == null || !sr.red) &&
								(sl == null || !sl.red)) {
							xpr.red = true;
							x = xp;
						} else {
							if (sr == null || !sr.red) {
								if (sl != null)
									sl.red = false;
								xpr.red = true;
								root = rotateRight(root, xpr);
								xpr = (xp = x.parent) == null ?
										null : xp.right;
							}
							if (xpr != null) {
								xpr.red = (xp == null) ? false : xp.red;
								if ((sr = xpr.right) != null)
									sr.red = false;
							}
							if (xp != null) {
								xp.red = false;
								root = rotateLeft(root, xp);
							}
							x = root;
						}
					}
				} else { // symmetric
					if (xpl != null && xpl.red) {
						xpl.red = false;
						xp.red = true;
						root = rotateRight(root, xp);
						xpl = (xp = x.parent) == null ? null : xp.left;
					}
					if (xpl == null)
						x = xp;
					else {
						TreeNode<K, V> sl = xpl.left, sr = xpl.right;
						if ((sl == null || !sl.red) &&
								(sr == null || !sr.red)) {
							xpl.red = true;
							x = xp;
						} else {
							if (sl == null || !sl.red) {
								if (sr != null)
									sr.red = false;
								xpl.red = true;
								root = rotateLeft(root, xpl);
								xpl = (xp = x.parent) == null ?
										null : xp.left;
							}
							if (xpl != null) {
								xpl.red = (xp == null) ? false : xp.red;
								if ((sl = xpl.left) != null)
									sl.red = false;
							}
							if (xp != null) {
								xp.red = false;
								root = rotateRight(root, xp);
							}
							x = root;
						}
					}
				}
			}
		}

		static <K, V> boolean checkInvariants(TreeNode<K, V> t) {
			TreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right,
					tb = t.prev, tn = (TreeNode<K, V>) t.next;
			if (tb != null && tb.next != t)
				return false;
			if (tn != null && tn.prev != t)
				return false;
			if (tp != null && t != tp.left && t != tp.right)
				return false;
			if (tl != null && (tl.parent != t || tl.hash > t.hash))
				return false;
			if (tr != null && (tr.parent != t || tr.hash < t.hash))
				return false;
			if (t.red && tl != null && tl.red && tr != null && tr.red)
				return false;
			if (tl != null && !checkInvariants(tl))
				return false;
			if (tr != null && !checkInvariants(tr))
				return false;
			return true;
		}
	}
	//=========================使用到的节点结构==========================

}

