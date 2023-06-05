public class Main {
    public static void main(String[] args) {
        //connection with gui

        do {
            for(Player player : players){
                currentPlayer = player;
                int[] dice = currentPlayer.rollTheDice();
                if(checkIfDoubles(dice))
                    currentPlayer.numOfDoublesInARow ++;
                if(checkIfThreeDoublesInARow(currentPlayer.numOfDoublesInARow))
                    Jail.sendToJail(currentPlayer);
                int diceSum = dice[0] + dice[1];
                if(Jail.isInJail(currentPlayer))
                    break;
                else{
                    boolean isRelative = true;
                    movePlayer(currentPlayer, diceSum, isRelative);
                    if(currentPlayer.currentBlock instanceof Room){
                        if (currentPlayer.ownsProperty(currentPlayer.currentBlock)
                                && canBuildDesk((Room)currentPlayer.currentBlock, currentPlayer)){
                            //ask player if he wants to build desk
                            //use buildDesk accordingly
                        } else if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)){
                            boolean ownerExists = false;
                            for (Player p : players){
                                if (p.equals(currentPlayer))
                                    continue;
                                else{
                                    if (p.ownsProperty(currentPlayer.currentBlock)) {
                                        if (currentPlayer.balance >= ((Room) currentPlayer.currentBlock).calculateRent()){
                                            currentPlayer.balance -= ((Room) currentPlayer.currentBlock).calculateRent();
                                            p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
                                        }else {
                                            GameBoard.players.remove(player);
                                        }
                                        ownerExists = true;
                                        break;
                                    }
                                }
                            }
                            if (ownerExists == false){
                                //ask player if he wants to buy Room
                                //use buyProperty accordingly
                            }
                        }
                    }else if (currentPlayer.currentBlock instanceof Service){
                        if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)){
                            boolean ownerExists = false;
                            for (Player p : players){
                                if (p.equals(currentPlayer))
                                    continue;
                                else{
                                    if (p.ownsProperty(currentPlayer.currentBlock)) {
                                        if (currentPlayer.balance >= ((Service) currentPlayer.currentBlock).calculateRent(p,diceSum)){
                                            currentPlayer.balance -= ((Service) currentPlayer.currentBlock).calculateRent(p,diceSum);
                                            p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
                                        }else {
                                            GameBoard.players.remove(player);
                                        }
                                        ownerExists = true;
                                        break;
                                    }
                                }
                            }
                            if (ownerExists == false){
                                //ask player if he wants to buy Transport
                                //use buyProperty accordingly
                            }
                        }
                    }else if (currentPlayer.currentBlock instanceof Transport){
                        if (!currentPlayer.ownsProperty(currentPlayer.currentBlock)){
                            boolean ownerExists = false;
                            for (Player p : players){
                                if (p.equals(currentPlayer))
                                    continue;
                                else{
                                    if (p.ownsProperty(currentPlayer.currentBlock)) {
                                        if (currentPlayer.balance >= ((Transport) currentPlayer.currentBlock).calculateRent(p)){
                                            currentPlayer.balance -= ((Transport) currentPlayer.currentBlock).calculateRent(p);
                                            p.balance += ((Room) currentPlayer.currentBlock).calculateRent();
                                        }else {
                                            GameBoard.players.remove(player);
                                        }
                                        ownerExists = true;
                                        break;
                                    }
                                }
                            }
                            if (ownerExists == false){
                                //ask player if he wants to buy Transport
                                //use buyProperty accordingly
                            }
                        }
                    }else if (currentPlayer.currentBlock instanceof Tax){
                        //gui interaction to choose between 10% of balance or 200$
                        //use payTax accordingly
                    }else if (currentPlayer.currentBlock instanceof Action){
                        //needs connection to database in order to identify Decision or Chance
                    }
                }

                //needs break in case of currentPlayer bankruptcy
            }
        }while(true);
    }
}
