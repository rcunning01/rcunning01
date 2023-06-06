/* RCLinkedListTest
 * By Ryan Cunningham
 * For Computer Science II
 * Taught by Jeremiah Ramsey
 * Whatcom Community College
 * Bellingham, Washington, USA
 * Phone Book Assignment
 */

import java.util.Scanner;

public class RCLinkedListTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RCLinkedList phonebook = new RCLinkedList(
            null,
            null, 
            null, 
            null, 
            null, 
            null, 
            null, 
            null
        );

        System.out.println("There are no entries currently.");

        while (true) {
            System.out.println(
                "Type 'c' to create a new entry, 'd' to delete an entry,\n" +
                "'m' to modify an entry, 'v' to view all entries,\n" +
                "or 'q' to quit"
            );
            String input = sc.next();
            switch (input) {
                case "c":
                    sc.nextLine();
                    System.out.println("A response to all fields is " +
                                       "required.");
                    System.out.print("Entry's name ('Anonymous' for none)? ");
                    String name = sc.nextLine();
                    System.out.print("Entry's address " +
                                     "(one line - including city, region and " +
                                     "country)? ");
                    String address = sc.nextLine();
                    System.out.print("Entry's phone number ('0' for none)? ");
                    String phoneNum = sc.nextLine();
                    System.out.print("Entry's Web site " +
                                     "('http://example.com' for none)? ");
                    String webSite = sc.nextLine();
                    System.out.print("Entry's social network handles " +
                                     "('@' for none)? ");
                    String handles = sc.nextLine();
                    System.out.print("Entry's birthday (any format, " +
                                     "0000-00-00 for none)? ");
                    String birthday = sc.nextLine();
                    System.out.print("Entry's notes ('.' for none)? ");
                    String notes = sc.nextLine();
                    if (
                        name.equals("") ||
                        address.equals("") ||
                        phoneNum.equals("") ||
                        webSite.equals("") ||
                        handles.equals("") ||
                        birthday.equals("") ||
                        notes.equals("")
                    ) {
                        System.out.println("Invalid input");
                        break;
                    };
                    phonebook.add(
                        new RCLinkedList(
                            name,
                            address,
                            phoneNum,
                            webSite,
                            handles,
                            birthday,
                            notes,
                            null
                        )
                    );
                    break; // end case "c"
                case "m":
                    sc.nextLine();
                    System.out.println("A response to all fields is " +
                                       "required.");
                    System.out.print("Entry's old name? ");
                    String oldName = sc.nextLine();
                    System.out.print("Entry's new name " +
                                     "('Anonymous' for none)? ");
                    String newName = sc.nextLine();
                    System.out.print("Entry's new address " +
                                     "(one line - including city, region and " +
                                     "country)? ");
                    String newAddress = sc.nextLine();
                    System.out.print("Entry's new phone number " +
                                     "('0' for none)? ");
                    String newPhoneNum = sc.nextLine();
                    System.out.print("Entry's new Web site " +
                                     "('http://example.com' for none)? ");
                    String newWebSite = sc.nextLine();
                    System.out.print("Entry's new social network handles " +
                                     "('@' for none)? ");
                    String newHandles = sc.nextLine();
                    System.out.print("Entry's new birthday (any format, " +
                                     "0000-00-00 for none)? ");
                    String newBirthday = sc.nextLine();
                    System.out.print("Entry's new notes ('.' for none)? ");
                    String newNotes = sc.nextLine();
                    if (
                        newName.equals("") ||
                        newAddress.equals("") ||
                        newPhoneNum.equals("") ||
                        newWebSite.equals("") ||
                        newHandles.equals("") ||
                        newBirthday.equals("") ||
                        newNotes.equals("")
                    ) {
                        System.out.println("Invalid input");
                        break;
                    };
                    try {
                        phonebook.modify(
                            oldName,
                            newName,
                            newAddress,
                            newPhoneNum,
                            newWebSite,
                            newHandles,
                            newBirthday,
                            newNotes
                        );
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    break; // end case "m"
                case "d":
                    sc.nextLine();
                    System.out.print("Entry's old name? ");
                    String entryName = sc.nextLine();
                    if (entryName.equals(null)) {
                        System.out.println("Invalid input");
                        break;
                    }
                    try {
                        phonebook.delete(entryName);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    break;
                case "v":
                    if (!phonebook.getHasEntries()) {
                        System.out.println("No entries.");
                    } else {
                        System.out.println(phonebook.toString());
                    }
                    break;
                case "q":
                    System.out.println("You will lose all entries " +
                                       "in this phonebook.");
                    System.out.print("Are you sure (y/n)? ");
                    String confirm = sc.next();
                    switch (confirm) {
                        case "y":
                            sc.close();
                            System.exit(0);
                            break;
                        case "n":
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            } // end switch (input)
        } // end while (true)
    } // end public static void main(String[] args)
} // end public class RCLinkedListTest