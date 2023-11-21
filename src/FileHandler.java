import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class FileHandler {
    public LinkedList<Member> readFile(){
        LinkedList<Member> members = new LinkedList<>();
        File file = new File("members.csv");
        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
            return members;
        }
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()){
                char memberType =  scanner.next().charAt(0) ;
                int memberID = Integer.parseInt(scanner.next());
                String name = scanner.next();
                double fees = Double.parseDouble(scanner.next());
                int clubOrPoint = Integer.parseInt(scanner.next());
                System.out.printf("%s %d %s %.2f %d\n", memberType, memberID, name, fees, clubOrPoint);
                Member member = (memberType == 'S') ?
                        new SingleClubMember(memberType, memberID, name, fees, clubOrPoint) :
                        new MultiClubMember(memberType, memberID, name, fees, clubOrPoint);
                members.addLast(member);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return members;
    }
    public void appendFile(String mem){
        File file = new File("members.csv");
        try(FileWriter writer = new FileWriter(file, true)) {
            writer.append(mem);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void overwriteFile(LinkedList<Member> m){
        File file = new File("members.csv");
        File fileTemp = new File("members.temp");
        try(FileWriter writer = new FileWriter(fileTemp)){
            if(!fileTemp.exists())
                fileTemp.createNewFile();

            for (Member member : m){
                writer.append(member.toString());
            }
            writer.flush();
            writer.close();
            file.delete();
            fileTemp.renameTo(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}