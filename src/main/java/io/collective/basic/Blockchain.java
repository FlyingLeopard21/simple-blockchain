package io.collective.basic;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Blockchain {
    private List<Block> chainOfBlocks = new ArrayList<Block>();
    public boolean isEmpty() {
        return chainOfBlocks.isEmpty();
    }

    public void add(Block block) {
        chainOfBlocks.add(block);
    }

    public int size() {
        return chainOfBlocks.size();
    }

    public boolean isValid() throws NoSuchAlgorithmException {

        // check if blockchain isn't empty
        if(!chainOfBlocks.isEmpty()){
            // todo - check mined
            // call isMined() for each block in blockchain and compare value using assert function
            int index;
            for (index = 0; index < chainOfBlocks.size(); index++){
                if (!isMined(chainOfBlocks.get(index))){
                    return false;
                }
            }

            // todo - check previous hash matches
            // call getPreviousHash() for each block in blockchain and compare value using assert function
            if (!chainOfBlocks.get(0).getPreviousHash().equals("0")){
                return false;
            }
            for (index = 1; index < chainOfBlocks.size(); index++){
                if (!chainOfBlocks.get(index).getPreviousHash().equals(chainOfBlocks.get(index - 1).getHash())){
                    return false;
                }
            }
            // todo - check hash is correctly calculated
            // call calculatedHash() for each block in blockchain and compare value using assert function
            for (index = 0; index < chainOfBlocks.size(); index++){
                if (chainOfBlocks.get(index).getHash().length() != 64){
                    return false;
                }
            }
            return true;
        }

        return true;

    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());
        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) {
        return minedBlock.getHash().startsWith("00");
    }
}