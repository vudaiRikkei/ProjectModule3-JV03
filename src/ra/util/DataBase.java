package ra.util;

import java.io.*;

public class DataBase {
    public static final String USER_PATH ="C:\\Users\\hung1\\OneDrive\\Desktop\\Project_MD3\\src\\ra\\util\\users.txt";
    public static void writeToFile(Object o, String path){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos= new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(oos != null){
                    oos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                if(fos != null){
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public  static  Object readFromFile(String path){
        FileInputStream fis  = null;
        ObjectInputStream ois =null;
        Object o = null;
        try {
            fis= new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            o = ois.readObject();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(ois != null){
                    ois.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                if(fis != null){
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return o;
    }
}
