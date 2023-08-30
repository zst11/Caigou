import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Test_ObjectPool {
    public static void main(String[] args) {
        ObjectPool objectPool = new ObjectPool();
        objectPool.initPool();
        System.out.println(objectPool.getObject("StudentA"));
        System.out.println(objectPool.getObject("TeacherA"));
    }
}
class ObjectPool{
//    维护一个map集合
    Map<String,Object> map;
//    准备一个用来创建对象的数据集合
    Map<String,String> dataMap;
    {
        map = new HashMap<>();
        dataMap = new HashMap<>();
        dataMap.put("StudentA", "StudentA");
        dataMap.put("TeacherA", "TeacherA");
    }
//    初始化池子的方法
    public void initPool(){
        dataMap.forEach((k,v)->{
            try {
                Class<?> clazz = Class.forName(v);
//                反射获取空参构造器需提供空参构造器，默认的不行
                Constructor<?> constructor = clazz.getConstructor(null);
                if (constructor == null) {
                    throw new RuntimeException(clazz.getName()+",空参构造器不存在");
                }
                Object o = constructor.newInstance();
                map.put(k,o);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
//    获取对象的方法
    public Object getObject(String name){
        return map.get(name);
    }
}
class StudentA{
    public StudentA(){}
}
class TeacherA{
    public TeacherA(){}
}