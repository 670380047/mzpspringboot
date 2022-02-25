package util.study.Designpattern.adapter.duotai;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/18 18:10
 * @File : TestClass
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 测试多态
 * 3.继承了抽象方法的普通方法。 属于第三等级
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/18 18:10
 */
public class TestClass extends TestAbstract {

    public  String name;  // 测试继承关系:  子类可以自己接访问。也可以通过访问器来访问
    protected Integer score;
    String sex;
    private Integer age;    // 测试继承关系

    /**
     * 静态方法也可以继承.但不存在重写的情况。只存在隐藏(如果你在子类中又定义了一个相同签名的静态方法，那么父类的静态方法将被隐藏，而不是重写)
     */
    public static void myStatic(){
        System.out.println("TestClass类的myStatic静态方法（来自于TestClass类）");
    }

    @Override
    void eat() {
        System.out.println("TestClass的eat方法（eat方法的根源在TestAbstract抽象类中）");
    }

    @Override
    public void say() {
        System.out.println("TestClass的say方法（say方法的根源在TestInterface接口中）");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
