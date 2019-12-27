package oop.permissionmodifier;

/**
 * 提问：四个权限修饰符，哪些可以用来修饰接口。
 * 答：public和缺省可以，protected和private不可以。
 * 
 * 提问：为什么四个权限修饰符中，只能用public和缺省来修饰接口？
 * 答：
 * 	①protected为什么不行：
 * 		首先我们要明确protected的作用：被protected修饰的东西（如字段、方法），在类内部、同一个包中、子类这三个地方都可以访问到，
 * 		而所谓的“子类”中也可以访问，这是介于“同一个包”和“其他包”之间的一个范围，即不在同一个包，但又不是其他包中所有的地方都可以访问。
 * 		而对于一个接口或一个类而言，其存在的位置就只有“是否在同一个包”，在子类中是否可以访问这往往是对于字段、方法而言的；
 * 	②private为什么不行：如果一个接口可以声明为私有的，那就意味着别人都无法访问它，那它存在的意义是什么？
 * 
 * @author May
 * 2019年12月26日
 */
public interface A01_ModifyInterfaceAndClass {// √
	
}

//protected interface A01_ModifyInterface1{// ×
//	
//}

interface A01_ModifyInterface2{// √ ；接口不写访问修饰符，即缺省。
	
}

//default interface A01_ModifyInterface3{// ×
//	
//}

//private interface A01_ModifyInterface4{// ×
//	
//}

interface A01_ModifyInterface5 extends A01_ModifyInterfaceAndClass{
	
}