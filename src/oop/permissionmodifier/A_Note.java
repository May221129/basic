package oop.permissionmodifier;

/**
 * 四种权限修饰符：public,protected,缺省（default），private。
 *
 * 一、各个权限修饰符可以用于修饰什么：
 * 						 public		protected		缺省（default）		private		测试
 * 	1. 接口：				√			×					√				×		A01_ModifyInterface
 * 	2. 类（抽象类+普通类）：	√			×					√				×		同上
 * 	3. 内部类：				√			√					√				√		A01_ModifierInnerClass
 * 	4. 字段：				√			√					√				√		A01_ModifyField
 * 	5. 方法：				√			√					√				√		同上
 * 
 * 二、各个权限修饰符的访问范围：
 * 					public		protected		缺省（default）		private
 * 	1. 不同包			√			×					×				×
 * 	2. 子类				√			√					×				×
 * 	3. 同一包 			√			√					√				×
 * 	4. 类内部			√			√					√				√
 * 	5. 用权限修饰符来修饰内部类时，被修饰的内部类的可访问范围比较特殊：A01_ModifierInnerClass
 * 		
 * @author May
 * 2019年12月26日
 */
public class A_Note {

}
