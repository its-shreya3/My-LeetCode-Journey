class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean aliceTurn = true;

        while (n >= remove) {
            n -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }

        // If it's currently Alice's turn and she can't move, she loses (so !aliceTurn).
        return !aliceTurn;
    }
}
