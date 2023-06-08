package src.main.java.files;

public abstract class Block {
    private int blockPosition;

    private String blockTitle;
    public Block(int position, String title) {
        this.blockPosition = position;
        blockTitle = title;
    }

    public String getBlockTitle() {
        return blockTitle;
    }

    public int getBlockPosition() {
        return blockPosition;
    }
}