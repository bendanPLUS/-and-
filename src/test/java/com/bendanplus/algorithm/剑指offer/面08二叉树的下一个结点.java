package com.bendanplus.algorithm.剑指offer;

import java.util.*;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class 面08二叉树的下一个结点 {

	public TreeLinkNode GetNext(TreeLinkNode pNode) {
		if (pNode == null)
			return null;
		//如果右子树不为null 则找到右子树的最左儿子 返回
		if (pNode.right != null) {
			TreeLinkNode node = pNode.right;
			while (node.left != null)
				node = node.left;
			return node;
		} else {
			//如果右子树为null
			while (pNode.next != null) {
				if (pNode.next.left == pNode)
					return pNode.next;
				pNode = pNode.next;
			}
		}
		return null;
	}


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final String str = in.next();
		if (str == null || "".equals(str))
			return;
		String[] split = str.split("");
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (!"".equals(split[i]) && map.containsKey(split[i]))
				map.put(split[i], map.get(split[i]) + 1);
			else if (!"".equals(split[i]))
				map.put(split[i], 1);

		}
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if (o2.getValue() == o1.getValue())
					return o1.getKey().compareTo(o2.getKey());
				return o2.getValue() - o1.getValue();
			}
		});

		Iterator<Map.Entry<String, Integer>> iter = list.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> item = iter.next();
			final String key = item.getKey();
			final Integer value = item.getValue();
			System.out.print(key + ":" + value + ";");
		}
	}
}
