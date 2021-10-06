package com.mmall.concurrency.设计模式;

import com.sun.corba.se.spi.orb.OperationFactory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// 计算类的基类
@Data
abstract class Operation {
	private double value1 = 0;
	private double value2 = 0;
	protected abstract double getResult();
}

//加法
class OperationAdd extends Operation {
	@Override
	protected double getResult() {
		return getValue1() + getValue2();
	}
}
//减法
class OperationSub extends Operation {
	@Override
	protected double getResult() {
		return getValue1() - getValue2();
	}
}
//乘法
class OperationMul extends Operation {
	@Override
	protected double getResult() {
		return getValue1() * getValue2();
	}
}
//除法
class OperationDiv extends Operation {
	@Override
	protected double getResult() {
		if (getValue2() != 0) {
			return getValue1() / getValue2();
		}
		throw new IllegalArgumentException("除数不能为零");
	}
}

//OperationFactory简单工厂类
class OperationFactory简单工厂 {

	public static Operation getOperation(String operation) {
		Operation oper = null;
		switch (operation) {
			case "add":
				oper = new OperationAdd();
				break;
			case "sub":
				oper = new OperationSub();
				break;
			case "mul":
				oper = new OperationMul();
				break;
			case "div":
				oper = new OperationDiv();
				break;
			default:
				throw new UnsupportedOperationException("不支持该操作");
		}
		return oper;
	}
}

//工厂方法模式
//工厂接口
interface IFactory工厂方法模式 {
	Operation CreateOption();
}

//加法类工厂
class AddFactory implements IFactory工厂方法模式 {
	public Operation CreateOption() {
		return new OperationAdd();
	}
}

//减法类工厂
 class SubFactory implements IFactory工厂方法模式 {
	public Operation CreateOption() {
		return new OperationSub();
	}
}

//乘法类工厂
class MulFactory implements IFactory工厂方法模式 {
	public Operation CreateOption() {
		return new OperationMul();
	}
}

//除法类工厂
class DivFactory implements IFactory工厂方法模式 {
	public Operation CreateOption() {
		return new OperationDiv();
	}
}

public class OperationTest简单工厂{
	public static void main(String[] args) {
		//计算两数之和
		OperationAdd operationAdd = new OperationAdd();
		operationAdd.setValue1(1);
		operationAdd.setValue2(2);
		System.out.println("sum:"+operationAdd.getResult());
		//计算两数乘积
		OperationMul operationMul = new OperationMul();
		operationMul.setValue1(3);
		operationMul.setValue2(5);
		System.out.println("multiply:"+operationMul.getResult());
		//计算两数之差。。。


		//简单工厂模式
		Operation operation = OperationFactory简单工厂.getOperation("add");
		operation.setValue1(1);
		operation.setValue2(2);
		System.out.println("简单工厂创建对象:"+operation.getResult());


		//工厂方法模式
		//减法
		IFactory工厂方法模式 subFactory = new SubFactory();
		Operation operationSub =  subFactory.CreateOption();
		operationSub.setValue1(22);
		operationSub.setValue2(20);
		System.out.println("sub:"+operationSub.getResult());
		//除法
		IFactory工厂方法模式 Divfactory = new DivFactory();
		Operation operationDiv =  Divfactory.CreateOption();
		operationDiv.setValue1(99);
		operationDiv.setValue2(33);
		System.out.println("div:"+operationSub.getResult());
	}
}