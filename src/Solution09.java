public class Solution09 {

    //lev17:
    //Не используя synchronized сделай так, чтобы количество сделанных и принятых предложений было одинаковым.
    volatile public static int proposal = 0;

    public static void main(String[] args) {
        new AcceptProposal().start();
        new MakeProposal().start();
    }//end void main

    public static class MakeProposal extends Thread {
        @Override
        public void run() {
             int thisProposal = proposal;

            while (proposal < 10) {
                System.out.println("Сделано предложение №" + (thisProposal + 1));
                proposal = ++thisProposal;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }//end class MakeProposal

    public static class AcceptProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal;

            while (thisProposal < 10) {
                if (thisProposal != proposal) {
                    System.out.println("Принято предложение №" + proposal);
                    thisProposal = proposal;
                }

            }
        }
    }//end class AcceptProposal
}
