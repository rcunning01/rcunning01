/* RCLinkedList
 * By Ryan Cunningham
 * For Computer Science II
 * Taught by Jeremiah Ramsey
 * Whatcom Community College
 * Bellingham, Washington, USA
 * Phone Book Assignment
 */

import java.util.Arrays;

public class RCLinkedList {
    private RCLinkedList next;
    private String name;
    private String address;
    private String phoneNum;
    private String webSite;
    private String handles;
    private String birthday;
    private String notes;

    public RCLinkedList(
            String name, // Contact's name
            String address, // Contact's address (includes city/country)
            String phoneNum, // Contact's phone number
            String webSite, // Contact's Web site
            String handles, // Contact's social networking handles
            String birthday, // Contact's birthday
            String notes, // Notes for contact
            RCLinkedList next // Next contact
    ) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.webSite = webSite;
        this.handles = handles;
        this.birthday = birthday;
        this.notes = notes;
        this.next = next;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setPhoneNum(String newPhoneNum) {
        this.phoneNum = newPhoneNum;
    }

    public void setWebSite(String newWebSite) {
        this.webSite = newWebSite;
    }

    public void setHandles(String handles) {
        this.handles = handles;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public String getWebSite() {
        return this.webSite;
    }

    public String getHandles() {
        return this.handles;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getNotes() {
        return this.notes;
    }

    private String[] toArray() {
        String[] listArr = {
            this.name,
            this.address,
            this.phoneNum,
            this.webSite,
            this.handles,
            this.birthday,
            this.notes
        };
        return listArr;
    }

    public String toString() {
        RCLinkedList loopList = this;
        RCLinkedList llNext = loopList.getNext();
        String returnStr = "";
        while (llNext != null) {
            llNext = loopList.getNext();
            if (llNext != null) {
                loopList = llNext;
                returnStr += Arrays.toString(loopList.toArray()) + "\n";
            }
        }
        return returnStr;
    }

    public boolean getHasEntries() {
        if (getNext() != null) {
            return true;
        } else {
            return false;
        }
    }

    private RCLinkedList getNext() {
        return this.next;
    }

    private void setNext(RCLinkedList nextObj) {
        this.next = nextObj;
    }

    public void add(RCLinkedList newEntry) {
        if (this.getNext() == null) {
            this.setNext(newEntry);
        } else {
            RCLinkedList loopList = this;
            RCLinkedList llNext = loopList.getNext();
            while (llNext != null) {
                llNext = loopList.getNext();
                if (llNext != null) {
                    loopList = llNext;
                }
            }
            loopList.setNext(newEntry);
        }
    }

    public void delete(
        String queryName // Name of entry to delete
    ) {
        if (this.name == queryName) {
            throw new IllegalArgumentException("Cannot delete the hidden null entry");
        } else {
            try {
                RCLinkedList loopList = this;
                if (loopList.getNext() != null) {
                    while (!loopList.getNext().name.equals(queryName)) {
                        loopList = loopList.getNext();
                    }
                    loopList.setNext(loopList.getNext().getNext());
                }
            } catch (java.lang.NullPointerException unused) {
                throw new IllegalArgumentException("No matches for '" + queryName + "'");
            }
        }
    }

    public void modify(
        String queryName, // Old name of entry to modify
        String newName, // Entry's new name
        String newAddr, // New address
        String newPhoneNum, // New phone number
        String newWebSite, // New Web site
        String newHandles, // New SNS handles
        String newBirthday, // New birthday
        String newNotes // New notes
    ) {
        if (this.name == queryName) {
            throw new IllegalArgumentException("Cannot modify the hidden null entry");
        } else {
            try {
                RCLinkedList loopList = this;
                if (loopList.getNext() != null) {
                    while (!loopList.getNext().name.equals(queryName)) {
                        loopList = loopList.getNext();
                    }
                    RCLinkedList entry = loopList.getNext();
                    entry.setName(newName);
                    entry.setAddress(newAddr);
                    entry.setPhoneNum(newPhoneNum);
                    entry.setWebSite(newWebSite);
                    entry.setHandles(newHandles);
                    entry.setBirthday(newBirthday);
                    entry.setNotes(newNotes);
                }
            } catch (java.lang.NullPointerException e) {
                throw new IllegalArgumentException("No matches for '" + queryName + "'");
            }
        }
    }
    /*
     * end public void modify(
     *     String queryName,
     *     String newName,
     *     String newAddr,
     *     String newPhoneNum,
     *     String newWebSite,
     *     String newHandles,
     *     String newBirthday,
     *     String newNotes
     * )
     */
} // end public class RCLinkedList
