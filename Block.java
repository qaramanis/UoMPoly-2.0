abstract class Block {
    public int blockPosition;

    private String blockTitle;
    public Block(int position, String title) {
        this.blockPosition = position;
        blockTitle = title;
    }

    public String getBlockTitle() {
        return blockTitle;
    }


}