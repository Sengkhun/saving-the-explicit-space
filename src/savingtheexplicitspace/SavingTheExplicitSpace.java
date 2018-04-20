/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savingtheexplicitspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sengkhunlim
 */
public class SavingTheExplicitSpace {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[] initVal = { 5, 6, 1, 2, 10, 7, 3, 20, 15 };
        
        // Create Tree
        Tree t = new Tree( initVal[0] );
        Node current = t.root;
        
        // First Level
        current.leftChild = new Node( initVal[1] ); // root->left
        current.rightChild = new Node( initVal[2] ); // root->right
        
        // Second Level
        current = t.root.leftChild;
        current.leftChild = new Node( initVal[3] ); // root->left->left
        current.leftChild.leftChild = new Node( initVal[4] ); // root->left->left->left
        current.leftChild.rightChild = new Node( initVal[5] ); // root->left->left->right
        
        current = t.root.rightChild;
        current.leftChild = new Node( initVal[6] ); // root->right->left
        current.rightChild = new Node( initVal[7] ); // root->right->right
        current.leftChild.rightChild = new Node( initVal[8] ); // root->right->left->right
        
        int goal = 7;
                
        if ( savingTheExplicitSpace(t, goal ) )
            System.out.println("Found");
        else
            System.out.println("Not Found");
        
    }
    
    public static boolean savingTheExplicitSpace( Tree t, int goal ) {
        
        Node n;
        int rand;
        
        // 1. Initialize: Set OPEN = { s }, CLOSED = { }
        List<Node> open = new ArrayList();
        List<Node> close = new ArrayList();
       
        open.add( t.root );
        
        // 2. Fail: if OPEN = { }, Terminate with failure
        while ( !open.isEmpty() ) {
            
            // 3. Select: Select a random state, n, from open and save n in close
            rand = randomNumber( open.size() );
            n = open.get( rand ); // Select a random state, n, from OPEN
            open.remove( rand ); // Remove that state
            close.add( n ); // Put n into close
                        
            // 4. Terminate: if n belong to goal, terminate with success
            if ( n.value == goal ) return true;
            
            // 5. Expand: Generate the successors of n using O. For each successor, m, insert m in OPEN only ifm∉[OPEN⋃CLOSED]
            if ( validateNode( n.leftChild, open, close ) )
                open.add( n.leftChild );
                      
            if ( validateNode( n.rightChild, open, close ) )
                open.add( n.rightChild );
            
        }
        
        return false;
        
    }
    
    public static int randomNumber( int length ) {
        
        Random rand = new Random();
        
        return rand.nextInt( length );
        
    }
    
    // return true when m ∉ [OPEN⋃CLOSED]
    public static boolean validateNode( Node n, List<Node> open, List<Node> close ) {
        
        if ( n != null ) {
            
            if ( open.indexOf( n ) == -1 && close.indexOf( n ) == -1 )
                return true;
            
        }
        
        return false;
    }
        
}
