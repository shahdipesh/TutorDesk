public class Utils {

  void generateMsg(String person, boolean status,String id){
      if(status){
          System.out.println(person+" with userid "+ id+ " successfully created.");
      }
      else{
          System.out.println("Duplicate"+ person +" with userid "+ id);
      }
  }

  boolean validateLength(String str){
      if(str.length()<=80 && !str.contains(" ")){
          return true;
      }
      else{
          return false;
      }
  }
}
