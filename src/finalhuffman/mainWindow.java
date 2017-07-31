package finalHuffman;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *********************************************************************************
 ******************Data Structures Project****************************************
 **********************Huffman Encoding*******************************************
 **************************Made By:***********************************************
 ********Faisal Shahzad https://github.com/Faisal-FS/Text-Compression-using-Huffman-encoding-with-GUI.git *******
 *********************************************************************************
 */
public class mainWindow extends javax.swing.JFrame {
    HuffmanEncode h;
    filling g ;
    /*
     * Constructor of MainWindows in which all gui components are initialized. 
     * Objects of two classes HuffmanEncode and filling are created which will be used later
     * in this class.
     */
    public mainWindow() {
        setTitle("Huffman Encoding Data Structures Project");
        setLocation(200,100);
        initComponents();
        h=new HuffmanEncode();
        g=new filling();
    }
 private void initComponents() {

        jButton1 = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        names = new javax.swing.JLabel();
        strPenal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        line = new javax.swing.JTextField();
        encodeBtn = new javax.swing.JButton();
        browseBtn = new javax.swing.JButton();
        decodeBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        savedBytes = new javax.swing.JLabel();
        savedBytes1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        decodedString = new javax.swing.JLabel();
        decodedStringTextField = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(740, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        title.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        title.setForeground(new java.awt.Color(0, 102, 102));
        title.setText("HUFFMAN ENCODING AND DECODING");
        getContentPane().add(title);
        title.setBounds(210, 0, 330, 40);

        names.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        names.setText("Made By: Faisal Shahzad (FA13-BCS-106) - Sohail Ahmad Khan (FA13-BCS-086)");
        getContentPane().add(names);
        names.setBounds(180,30, 400, 20);

        strPenal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        strPenal.setLayout(null);

        jLabel1.setText("Enter String");
        strPenal.add(jLabel1);
        jLabel1.setBounds(10, 30, 70, 30);
        strPenal.add(line);
        line.setBounds(80, 30, 590, 30);
        
        encodeBtn.setText("Encode String");
        encodeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					encodeBtnActionPerformed(evt);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
            }
        });
        strPenal.add(encodeBtn);
        encodeBtn.setBounds(250, 70, 120, 23);
        
        
        browseBtn.setText("Encode File");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                	browseBtnActionPerformed(evt);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
            }
        });
        strPenal.add(browseBtn);
        browseBtn.setBounds(100, 70, 120, 23);
        

        decodeBtn.setText("Decode");
        decodeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodeBtnActionPerformed(evt);
            }
        });
        strPenal.add(decodeBtn);
        decodeBtn.setBounds(400, 70, 120, 23);

        getContentPane().add(strPenal);
        strPenal.setBounds(10, 60, 700, 110);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        savedBytes.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        savedBytes.setForeground(new java.awt.Color(0, 0, 102));
        jPanel1.add(savedBytes);
        savedBytes.setBounds(110, 40, 460, 20);

        savedBytes1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        savedBytes1.setForeground(new java.awt.Color(0, 0, 102));
        jPanel1.add(savedBytes1);
        savedBytes1.setBounds(110, 10, 800, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 180, 700, 80);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        decodedString.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        decodedString.setText("Decoded String");
        jPanel2.add(decodedString);
        decodedString.setBounds(300, 10, 130, 30);

        decodedStringTextField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jPanel2.add(decodedStringTextField);
        decodedStringTextField.setBounds(10, 50, 670, 30);
        decodedStringTextField.setEditable(false);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 300, 700, 110);

        pack();
    }
    private void decodeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodeBtnActionPerformed
        triggerDecoding();
    }
    /*
     * this is method to call the decode method of the huffmanEncoding class. A decoded string 
     * will be retured and displayed in the gui window.
     * If decode button will be pressed before pressing encode than error will be displayed to 
     * encode your string first or the other option if you have codes.txt and encoded.exe files
     * already than just move them to program's directory so that they will be used to decode.
     */
    private void triggerDecoding(){
        try {
            String str = h.startDecoding();
            decodedStringTextField.setText(str);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "\nPlease encode string before decoding\nor copy encodedData and code files in this directory");
        }
    }
    
    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {//GEN-FIRST:event_encodeBtnActionPerformed
    	triggerFileEncoding();
    }
    
    private void encodeBtnActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {//GEN-FIRST:event_encodeBtnActionPerformed
    	triggerStrEncoding();
    }
    /*
     * This is the method to call the getSavedBytes() so that saved bytes will be retured.
     * If the given input string is less than 2 characters,i.e: 1 character , than an error 
     * dialogue box will be displayed. It is because when huffman encodes will be called than
     * huffman tree will only have one root node having that character than no codes will be assigned as
     * the root node will not have any child node to traverse.
     * Number of bytes saved will be displayed from the given input string.
     */
    String getText() throws FileNotFoundException{
    	JFileChooser fileopen = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("text files", "txt");
        fileopen.addChoosableFileFilter(filter);
        String txtData = "";
        int ret = fileopen.showDialog(null, "Open file to encode");

        if (ret == JFileChooser.APPROVE_OPTION) {
          File file = fileopen.getSelectedFile();
          Scanner scanf = new Scanner(file);
          while(scanf.hasNextLine()){
        	  txtData += scanf.nextLine();
          }
        }
        return txtData;
    }
    private void triggerFileEncoding() throws FileNotFoundException{
    	String str = this.getText();
    	h.setLine(str);
        int strLength = str.length();
        if(strLength < 2){
            JOptionPane.showMessageDialog(this, "String cannot be less than 2 characters\nPlease renter");
            dispose();
            new mainWindow().setVisible(true);
        }else{
            savedBytes1.setText("Entered string consumes "+strLength+" Bytes OR "+(strLength/1024)+" KB");
            savedBytes.setText("After encoding "+getSavedBytes(str,strLength)+" Bytes saved");

        }
    }
    private void triggerStrEncoding() throws FileNotFoundException{
        String str = line.getText();

    	h.setLine(str);
        int strLength = str.length();
        if(strLength < 2){
            JOptionPane.showMessageDialog(this, "String cannot be less than 2 characters\nPlease renter");
            dispose();
            new mainWindow().setVisible(true);
        }else{
            savedBytes1.setText("Entered string consumes "+strLength+" Bytes");
            savedBytes.setText("After encoding "+getSavedBytes(str,strLength)+" Bytes saved");
        }
    }
    /*
     * This is the method to call encoding method of huffmanEncoding class.
     * Encoded String length will be retured which will be converted into bytes and than subtracted from 
     * input string length to get saved bytes.
     * 
     */
    private String getSavedBytes(String str,int strLength){
        int Bytes = h.startEncoding();
        Bytes = (strLength-1) - (Bytes / 8);
        return Integer.toString(Bytes);
    }
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });

    }

    private javax.swing.JButton decodeBtn;
    private javax.swing.JLabel decodedString;
    private javax.swing.JTextField decodedStringTextField;
    private javax.swing.JButton encodeBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton browseBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField line;
    private javax.swing.JLabel names;
    private javax.swing.JLabel savedBytes;
    private javax.swing.JLabel savedBytes1;
    private javax.swing.JPanel strPenal;
    private javax.swing.JLabel title;
}

class HuffmanEncode{
    String line;
    PQueue freqCheck;
    BT huffmanTree;
    filling f;
    /*
     * Contructor of HuffmanEncode class in which 3 objects of BT,PQueue and filling will be 
     * created.
     */
    HuffmanEncode(){
        huffmanTree=new BT();
        this.freqCheck = new PQueue();
        f = new filling();
        this.line=null;
        consoleWelcome();
    }
    private void consoleWelcome(){
    	System.out.println("Huffman Encoding Data Structures Project by \n"
    			+ "Faisal Shahzad (FA13-BCS-106)\nSohail Ahmad Khan (FA13-BCS-086)\n"
    			+ "Console output of project .....");
    }
    void setLine(String line){
    	System.out.println("\nInput String: "+line+"\n");
        this.line=line;
    }
    /*
     * In this method files codes.txt and encoded.exe are desroyed if they exists.
     * Then frequencies are calculated in the linked list and than it will be sorted in 
     * ascending order to make this linked list a priority queue data structure.
     * Than it will be displayed on the console output.
     * At last encode() function will be called from which encoded string length will be returned 
     * and returned to where this function is called.
     */
    int startEncoding(){
        f.checkFilesBeforeEncoding();
        calculateFreq();
        freqCheck.orderAsc();
        freqCheck.display();
        return encode();
    }
    /*
     * In this function files which are to be decode are first checked whether they exist or not
     * and if not then an exception will be thrown.
     * After that f.read() will read the codes from codes.txt file and store that codes in the
     * Binary Tree to be later used for decoding and root node will be returned.
     * f.decodeFile() function will use that retured root node to decode the string.
     * 
     */
    String startDecoding() throws FileNotFoundException{
        f.checkFilesBeforeDecoding();
        return f.decodeFile(f.read());
    }
    /*
     * In this method freqCheck object will be used to store characters and their frequency in 
     * the linked list.
     * If the character already exists in the queue than its frequency will incremented else
     * a new node will be added having an initial frequency of 1.
     */
    void calculateFreq(){
    	char currChar;
        for(int i=0;i<line.length();i++){
        		currChar = line.charAt(i);
                PQNode charExists=freqCheck.exists(currChar); //currCharacter is checked whether it exists or not
                if(charExists!=null){
                    if(currChar==charExists.info.data){
                        int freq= (Integer) charExists.info.freq; // Existing character freq is retrieved ,incremented and returned to the node
                        charExists.info.freq = ++freq;
                    }
                }else{
                        BTNode<Integer> newNode=new BTNode(currChar,1);
                        freqCheck.insert(newNode); 
                }
        }
    }
    /*
     * In this method a while loop will be started until priority queue has only one element.
     * In the loop first two elements will be dequeued and than become to childs of a new 
     * BT node their frequencies sum will added in the new root.
     * After that new root will again added to the priority queue in its appropriate position.
     * After the loop last element will be dequeued from queue and then its info, which is of 
     * BTNode type , will be the root node of huffmanTree.
     * than this huffmanTree huffCodes function will be called to calculate the codes of 
     * each character.
     * These codes will be displayed in the console output.
     * At last these codes will be used to encode to input string.
     */
    int encode(){
        PQNode firstElement,secondElement;
        BTNode newRoot;
        while(freqCheck.count > 1 ){
            firstElement=freqCheck.deQueue();
            secondElement=freqCheck.deQueue();
            newRoot = new BTNode ('*',(Integer) firstElement.info.freq+ (Integer) secondElement.info.freq,firstElement.info,secondElement.info); // new root BTNode is made in which there are 4 arguments(char data,sum of dequeued elements frequencies, first element ,second element)
            freqCheck.insert(newRoot); //new root is inserted back to priority queue
        }
        huffmanTree.root=freqCheck.deQueue().info; // last element is dequeued and its info(BTNode type) is assigned as root.
        huffmanTree.huffCodes();
        try {
            return f.encodeFile(line); // calling of method in which input string in encoded
        } catch (IOException ex) {
            Logger.getLogger(HuffmanEncode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
class PQNode{
    PQNode next;
    BTNode info;
    /*
     * PQNode has info of BTNode type so that each leaf node of binary tree will be added in the queue.
     */
    public PQNode() {
        this.next = null;
    }
    public PQNode(BTNode info,PQNode next) {
        this.next = next;
        this.info = info;
    }
}
class PQueue{
    PQNode head;
    int count;
    PQueue(){
        head=null;
        count=0;
    }
    boolean isEmpty(){
        return head==null;
    }
    /*
     * In this method if queue is empty than a new node will be created else
     * the data will be added in its appropriate position.
     */
    void insert(BTNode data){
        PQNode newNode;
        if(head==null){
            newNode=new PQNode(data,null);
            head=newNode;
            count++;
        }else{
            PQNode prev = getNode(data);
            newNode=new PQNode(data,prev.next);
            prev.next = newNode;
            count++;
        }
    }
    /*
     * This method will traverse the queue and if current node has the data which is greater than
     * given data than its previous node will be returned.
     */
    PQNode getNode(BTNode node){
    	PQNode prev = head;
    	for(PQNode temp=head;temp!=null;temp=temp.next){
    		prev=temp;
    		if((Integer)temp.info.freq > (Integer) node.freq)
    			return prev;
    	}
    	return prev;
    }
    /*
     * This method uses bubble sort to sort the list in ascending order.
     * In each iteration current node freq will be checked against its next nodes frequency
     * whether it is greater or not than data swapped if it is greater.
     */
    void orderAsc(){
         PQNode curr,curr2;
         for(curr=head;curr!=null;curr=curr.next){
             for(curr2=head;curr2.next!=null;curr2=curr2.next){
                 if((Integer)curr2.info.freq > (Integer) curr2.next.info.freq){
                     BTNode temp=curr2.info;
                     curr2.info=curr2.next.info;
                     curr2.next.info=temp;
                 }
             }
         }
    }
    /*
     * This method will dequeue the front element of the priority queue.
     * count will be decremented.
     */
    PQNode deQueue(){
        PQNode temp=head;
        head=head.next;
        count--;
        return temp;
    }
    /*
     * This method will check whether given character exists in the list or not.
     */
    PQNode exists(char data){
        for(PQNode temp=head;temp!=null;temp=temp.next){
            if(temp.info.data==data){
                return temp;
            }
        }
        return null;
    }
    /*
     * This method will display data of the list.
     */
    void display(){
        System.out.println("Displaying Priority Queue");
        for(PQNode temp=head;temp!=null;temp=temp.next){
            System.out.println("char "+temp.info.data+" freq "+temp.info.freq);
        }
        System.out.println("");
    }
}
class BTNode<E>{
    BTNode left,right;
    char data;
    E freq;
    /*
     * This class is Binary Tree Node class which is of generic type freq variable because
     * when making huffman tree it has integer type freq and 
     * when saving codes from file to binary tree it has String type freq.
     */
    public BTNode( char data, E freq,BTNode left, BTNode right) { // this will be used in making huffman tree
        this.left = left;
        this.right = right;
        this.data = data;
        this.freq = freq;
    }
    BTNode(char data,E freq){
        this.data=data;
        this.freq=freq;
        left=right=null;
    }
    BTNode(){
        this.data='*';
        left=right=null;
    }
}
class BT<E>{
    BTNode root;
    filling f;
    BT(){
        root=null;
    }
    boolean empty(){
        return root==null;
    }
    void destroy(){
        root=null;
    }
    void insert(char data,E freq){
        root = insert(root,data,freq);
    }
    /*
     * In this insert method data will be added recursively using binary search tree concept.
     * If character data is less than current data in the node than left child will be called else
     * right child will be called.
     * This recursive calls occur until left child or right child is null.
     * It is now the base case then a new BTNode will be created with the data.
     */
    private BTNode insert(BTNode node, char data,E freq){
        if (node == null)
            node = new BTNode(data,freq);
        else{
            if (data < node.data)
                node.left = insert(node.left, data,freq);
            else
                node.right = insert(node.right, data,freq);
        }
        return node;
    }
    String getCode(char data){
        return getCode(root,data);
    }
    /*
     * In this method given character will searched in the tree and if found that nodes freq ,i.e; String type code will be returned.
     * In the iteration if char data is less than current node char data then, that nodes left child 
     * will be called else right child.
     */
    private String getCode(BTNode r, char data){
             String code = null;
             while ((r != null) && code==null )
             {
                char rData = r.data;
                if (data < rData)
                    r = r.left;
                else if (data > rData)
                    r = r.right;
                else{
                    code = (String)r.freq;
                    break;
                }
                 code = getCode(r, data);
             }
             return code;
    }
    /*
     * In this method new filling object will created so that huffcodes are saved on file.
     * huffCodes(root,code); will be used to make codes and save that codes to the string.
     * After that string having all the codes will be saved to the file.
     * 
     */
    void huffCodes(){
        f=new filling();
        String code="";
        System.out.println("Displaying calculated codes of each character");
        huffCodes(root,code);
        f.addCodeToFile();
    }
    /*
     * In this method root node and string will be passed.
     * preorder traverse will occur until a leaf node is found.
     * When going left 0 will be added in the code and going right 1 will be added in the code.
     * If a leaf node is found than this code will be displayed in the console output and added to
     * the string to be written to the file.
     */
    void huffCodes(BTNode r,String code){
             if (r.left!= null && r.right!=null){
                 huffCodes(r.left,code+"0");
                 huffCodes(r.right,code+"1");
             }else{
                 System.out.println("char = "+ r.data+" code = "+ code);
                 f.addCodeToString(r.data, code);
             }
    }
}
class filling {
    String charCodes;
    filling(){
        charCodes="";
    }
    /*
     * This method is called before decoding if codes.txt and encodedData.exe is not found than
     * an exception will thrown.
     */
    void checkFilesBeforeDecoding() throws FileNotFoundException{
        File code = new File("code.txt");
        File encoded = new File("encodedData.exe");
        if(!code.exists() && !encoded.exists()){
            throw new FileNotFoundException();
        }
    }
    /*
     * This method is used before encoding, if codes.txt and encodedData already exists than they
     * will be deleted to avoid data over writing or adding at end.
     */
    void checkFilesBeforeEncoding(){
        File code = new File("code.txt");
        File encoded = new File("encodedData.exe");
        if(code.exists() && encoded.exists()){
            code.delete();
            encoded.delete();
        }
    }
    /*
     * In this method given char sym and string code will be added in the charCodes string
     * including "sep" which later will be used.
     */
    
    void addCodeToString(char sym,String code){
        charCodes += sym + "sep"+code + "\n";
    }
    /*
     * This method just write charCode string to the file.
     */
    void addCodeToFile(){
        FileWriter codeFile= null;
        try {
            codeFile = new FileWriter("code.txt",true);
            try (BufferedWriter buffCode = new BufferedWriter(codeFile)) {
                buffCode.write(charCodes);     
            }
        } catch (IOException ex) {
            Logger.getLogger(filling.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                codeFile.close();
            } catch (IOException ex) {
                Logger.getLogger(filling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    /*
     * This method is used to store the codes from file to the binary tree according to their codes.
     * Basically, each line in the codes.txt has three things character, separator string sep and code.
     * Each line is splitted using sep delimiter and that splitted two strings are saved into string array.
     * Now this string array has character in its index 0 and code index 1.
     * A for loop is started until it has int greater than the code index 1 length.
     * Each character of that code, i.e; 0 or 1, is added to the tree according to their nature.
     * If it is 0 than tree left child is traversed iteratively until it has null and this is added.
     * else inverse of left child.
     * After for loop is terminated now we know that code has finished than string array index 0 char
     * is added in the current node.
     * At end all the characters will be at leaf nodes.
     */
    BTNode read(){
        BT t=new BT();
        BTNode p=null;
        t.root=new BTNode();
        File f= new File("code.txt");
        Scanner scan;
        try {
            scan = new Scanner(f);
            while(scan.hasNextLine()){
                p=t.root;
                String s= scan.nextLine();
                String[] code=s.split("sep");
                if(code[0].equals("codeLength"))
                    break;
                for(int i=0;i<code[1].length();i++){
                    if(code[1].charAt(i)=='0'){
                        if(p.left==null){
                            p.left=new BTNode('*',0);
                            p=p.left;
                        }else{
                            p=p.left;
                        }

                    }else{
                        if(p.right==null){
                            p.right=new BTNode('*',1);
                            p=p.right;
                        }else{
                            p=p.right;
                        }
                    }
                }
                p.data=code[0].charAt(0);
            }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        return t.root;
    }
    /*
     * This method is used to decode the encoded string from the file.
     * At first is only scan code length and free bits from the codes.txt file.
     * Code length is the length of the encodedString which is used later for the termination of for loop
     * Free bits are bits that are not used in the decoded string but to save that encoded string to the
     * file. This is because in each byte there are 8 bits and this encoded string is first converted to
     * byte and then saved. 
     * If the length of encodedString is 14 bits which means 2 free bit because there are 16bits in 2 bytes.
     * so we add these free bits at the end of the encoded string to be saved properly to file.
     */
    String decodeFile(BTNode root){
        String decodedString="";
        try {
            Scanner scanCodeLength = new Scanner(new File("code.txt"));
            int codeLength = 0;
            int freeBits = 0;
            while(scanCodeLength.hasNextLine()){
                String line = scanCodeLength.nextLine();
                String[] c = line.split("sep");
                if(c[0].equals("freeBits")){
                    freeBits = Integer.parseInt(c[1]);
                }
                if(c[0].equals("codeLength")){
                    codeLength = Integer.parseInt(c[1]);
                }
            }
            /*
             * decode method will convert bits in to String type for decoding.
             * This string is iterated character by character. 
             * If 0 then left child of the tree and if 1 then right child. At any time if 
             * a leaf node is found than it has our character which added to the decodedString.
             */
            String encoded = decode(codeLength);
            if(freeBits!=0){
                encoded=encoded.substring(0, (encoded.length()-freeBits));
            }
            BTNode p = root;
            System.out.println("\nYour Decoded String ....");
            for(int i =0;i<encoded.length();i++){
                if(encoded.charAt(i)=='0'){
                    if(p!=null){
                        p=p.left;
                        if(p.left==null && p.right == null){
                            System.out.print(p.data);
                            decodedString+=p.data;
                            p=root;
                        }
                    }
                }else{
                    if(p!=null){
                        p=p.right;
                        if(p.left==null && p.right == null){
                            System.out.print(p.data);
                            decodedString+=p.data;
                            p=root;
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(filling.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(filling.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decodedString;
    }
    /*
     * In this method encoded data is read from the file then converted into bits in BitSet class.
     * Then this BitSet class class object is converted to string , if it is true than 1 is added else
     * 0 is added in the string.
     */
    String decode(int codeLength) throws FileNotFoundException, IOException{
        String txt="";
        BitInputStream fin = new BitInputStream(new BufferedInputStream(new FileInputStream("encodedData.exe")));
        BitSet input = new BitSet();
        for (int index = 0; index < codeLength; index++){
            input.set(index, fin.read());
        }
        
        fin.close();
        for (int index = 0; index < codeLength; index++){
            txt+=input.get(index)? "1": "0";
        }
        return txt;
    }
    
    /*
     * This method will convert String type encoded String into BitSet obj, true or false which is 
     * then converted to bytes and saved to the file.
     */
    void encode(String txt) throws IOException{
        BitSet output = new BitSet();
        for (int index = 0; index < txt.length(); index++){
            output.set(index, (txt.charAt(index) != '0')); 
        }
        BitOutputStream fout = new BitOutputStream(new BufferedOutputStream(new FileOutputStream("encodedData.exe")));

        for (int index = 0; index < txt.length(); index++){
            fout.write(output.get(index));
        }
        
        fout.close();

     
    }
    /*
     * In this method character and their codes are inserted into Binary Tree from the codes.txt file.
     * After that input string is iterated and each character's codes is retrieved from the tree
     * using getCode method and added to the String c.
     * Then if its length is divisible by 8 it means it has no free bits else
     * free bits are calculated and added to codes.txt file to be later used to decoding.
     */
    int encodeFile(String text) throws IOException{
        BT<String> codesTree=new BT();
        try {
            FileReader codeFile= new FileReader("code.txt");
            Scanner scanFile = new Scanner(codeFile);
            while(scanFile.hasNextLine()){
                String line = scanFile.nextLine();
                String[] codes= line.split("sep");
                codesTree.insert(codes[0].charAt(0), codes[1]);
            }
            String c = "";
            for(int i=0;i<text.length();i++){
                c+=codesTree.getCode(text.charAt(i));
            }
            System.out.println("\nYour input string after encoding \n"+c);
            int codeLength  = c.length();
            if(codeLength % 8 == 0){
                try (BufferedWriter buffCode = new BufferedWriter(new FileWriter("code.txt",true))) {
                    buffCode.write("codeLengthsep"+codeLength+"\n");
                }
                encode(c);
                return codeLength;
            }else{
                int freeBits=(8-codeLength%8);
                    System.out.println("\nFree bits in the encoding = "+ freeBits);
                for(int a=0;a<freeBits;a++)
                    c+="0";
                codeLength+=freeBits;
                try (BufferedWriter buffCode = new BufferedWriter(new FileWriter("code.txt",true))) {
                    buffCode.write("codeLengthsep"+codeLength+"\n");
                    buffCode.write("freeBitssep"+freeBits+"\n");
                }
                /*
                 * This method is used to convert string type code into BitSet object to be saved to file.
                 */
                encode(c);
                return codeLength;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(filling.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
}

class BitInputStream {
    private InputStream in = null;
    private boolean[] buffer = new boolean[8];
    private int count = 8;
    
    public BitInputStream(InputStream in) {
        this.in = in;
    }
    /*
     * This method is used to convert bytes into bits from the encodedData.exe file.
     * Each is read from file and then convert it into boolean array of length 8.
     * 
     */
    public boolean read() throws IOException {
        if (this.count == 8){
            byte[] byteArray = new byte[1];
            this.in.read(byteArray);
            int num = (int)byteArray[0] + 128;

            for (int index = 0; index < 8; index++){
                this.buffer[index] = (num%2 == 1);
                num /= 2;
            }

            this.count = 0;
        }

        this.count++;

        return this.buffer[8-this.count];
    }

    public void close() throws IOException {
        this.in.close();
    }
}
class BitOutputStream {
    private OutputStream out = null;
    private int buffer = 0;
    private int count = 0;

    public BitOutputStream(OutputStream out) {
        this.out = out;
    }
    /*
     * This method is used to convert bits into bytes then saved to the file.
     * Each bit is stored into buffer and at any buffer reaches 8 it is type casted to byte and then 
     * saved to the file.
     */
    public void write(boolean x) throws IOException {
        this.buffer = this.buffer*2 + (x ? 1 : 0);
        this.count++;

        if (this.count == 8){
            this.out.write((byte)(this.buffer - 128));
            this.buffer = 0;
            this.count = 0;
        }
    }

    public void close() throws IOException {
        this.out.write((byte)(this.buffer - 128));
        this.out.close();
    }
}
