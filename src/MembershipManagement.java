import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    private final Scanner reader = new Scanner(System.in);
    private int getIntInput(){
        while (!reader.hasNextInt()){
            reader.nextLine();
        }
        return reader.nextInt();
    }
    private void printClubOptions(){
        System.out.println("1) Club Mercury\n" + "2) Club Neptune\n" + "3) Club Jupiter\n" + "4) Multi Clubs\n");
    }
    public int getChoice(){
        System.out.println("\nДОБРО ПОЖАЛОВАТЬ В ФИТНЕС-ЦЕНТР OZONE\n" +
                "================================\n" +
                "1) Добавить посетителя\n" +
                "2) Удалить посетителя\n" +
                "3) Отображать информацию о посетителе\n");
        System.out.print("Пожалуйста, выберите опцию (или введите -1, чтобы выйти): ");
        int choice = getIntInput();
        return choice;
    }
    public String addMembers(LinkedList<Member> list){
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal = n -> {
            switch (n){
                case 1: return 900;
                case 2: return 950;
                case 3: return 1000;
                case 4: return 1200;
                default: return -1;
            }
        };
        System.out.print("Введите имя: ");
        name = reader.next();
        printClubOptions();
        System.out.print("Выберите тип абонемента: ");
        club = getIntInput();
        fees = cal.calculateFees(club);
        memberID = (list.size() == 0) ? 1 : list.getLast().getMemberID() + 1;
        mbr = (club == 4) ?
                new MultiClubMember('M', memberID, name, fees, 100) :
                new SingleClubMember('S', memberID, name, fees, club);
        list.addLast(mbr);
        mem = mbr.toString();
        System.out.println("Добавлен посетитель :\n" + mem);

        return mem;
    }
    public void removeMember(LinkedList<Member> members){
        int memberID;
        boolean isRemoved = false;
        do{
            System.out.print("Введите №, удаляемого посетителя (-1 для выхода): ");
            memberID = getIntInput();
            for (Member m : members) {
                if(m.getMemberID() == memberID) {
                    isRemoved = members.remove(m);
                    break;
                }
            }
            if(!isRemoved)
                System.out.println("Посетитель с таким № отсутствует.");
        } while (!isRemoved && memberID != -1 );
        System.out.println("Посетитель с № " + memberID + " удалён.");
    }
    public void printMemberInfo(LinkedList<Member> members) {
        int memberID;
        boolean isFound = false;
        do{
            System.out.print("Введите № посетителя для просмотра информации о нём (-1 для выхода): ");
            memberID = getIntInput();
            for (Member m : members) {
                if(m.getMemberID() == memberID) {
                    isFound = true;
                    String clubOrPoint = (m.getMemberType() =='M') ? "клуб" : "Баллы";
                    int clubOrPointNum = (m.getMemberType() =='S') ? ((SingleClubMember)m).getClub() : ((MultiClubMember)m).getMembershipPoints();
                    System.out.printf("[ Тип абонимента = %s  № = %d  Имя = %s  Сборы = %.2f  %s = %d ]\n",
                            m.getMemberType(), m.getMemberID(), m.getName(), m.getFees(), clubOrPoint, clubOrPointNum);
                    break;
                }
            }
            if(!isFound)
                System.out.println("Посетитель с таким № отсутствует.");
        } while (!isFound && memberID != -1 );
    }
}