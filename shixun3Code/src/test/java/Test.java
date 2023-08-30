import java.io.*;

public class Test {
    public static void main(String[] args) {
//        new Exception()
        File fileAddress = new File("D:\\github_code\\Caigou\\shixun3Code\\src\\test\\java\\test.txt");
        File fileStudent = new File("D:\\github_code\\Caigou\\shixun3Code\\src\\test\\java\\testStudent.txt");
        FileReader in = null;
        BufferedReader bf = null;
        String[] valueAddress = new String[3];
        String[] valueStudent = new String[2];
        int k = 0;
        int k1 = 0;
        try {
            in = new FileReader(fileAddress);
            bf = new BufferedReader(in);
            String line = null;
            while ((line=bf.readLine())!=null){
                if (line.contains(",")){
                    String[] split = line.split(",");
                    valueAddress[k++] = split[1];
                }
            }
            in = new FileReader(fileStudent);
            bf = new BufferedReader(in);
            while ((line=bf.readLine())!=null){
                if (line.contains("propertyValue")){
                    String[] split = line.split(",");
                    valueStudent[k1++] = split[1];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                in.close();
                bf.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String[] value = new String[3];
        for (int i = 0; i < valueAddress.length; i++) {
            String[] split = valueAddress[i].split("=");
            value[i] = split[1];
        }
        Address address = new Address(value[0], value[1], value[2]);
        System.out.println(address);
        for (int i = 0; i < valueStudent.length; i++) {
            String[] split = valueStudent[i].split("=");
            value[i] = split[1];
        }
        Student student = new Student(Integer.parseInt(value[0]), value[1], address);
        System.out.println(student);
    }
}
class  Address{
    String province;
    String city;
    String street;

    public Address(String province, String city, String street) {
        this.province = province;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
class Student{
    Integer id;
    String name;
    Address address;

    public Student(Integer id,String name,Address address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name=" + name  +
                ", province=" + address.province +
                ", city=" + address.city +
                ", street=" + address.street +
                '}';
    }
}