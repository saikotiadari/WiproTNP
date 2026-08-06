public class JDBCCalls {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide an option parameter (1: Insert, 2: Delete, 3: Modify, 4: Display).");
            return;
        }

        int option = Integer.parseInt(args[0]);
        DAOClass dao = new DAOClass();

        switch (option) {
            case 1: 
                if (args.length >= 6) {
                    int rollno = Integer.parseInt(args[1]);
                    String name = args[2];
                    String standard = args[3];
                    String dob = args[4];
                    double fee = Double.parseDouble(args[5]);
                    dao.insert(rollno, name, standard, dob, fee);
                } else {
                    System.out.println("Insufficient arguments for insert!");
                }
                break;

            case 2: 
                if (args.length >= 2) {
                    int rollno = Integer.parseInt(args[1]);
                    dao.delete(rollno);
                } else {
                    System.out.println("Please provide roll number to delete!");
                }
                break;

            case 3: 
                if (args.length >= 3) {
                    int rollno = Integer.parseInt(args[1]);
                    double fee = Double.parseDouble(args[2]);
                    dao.modify(rollno, fee);
                } else {
                    System.out.println("Please provide roll number and new fee!");
                }
                break;

            case 4: 
                if (args.length >= 2) {
                    int rollno = Integer.parseInt(args[1]);
                    dao.display(rollno);
                } else {
                    dao.display(null);
                }
                break;

            default:
                System.out.println("Invalid Option Selected!");
                break;
        }
    }
}