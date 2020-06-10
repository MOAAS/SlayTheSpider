public class Application {
    public static void main(String[] args)  {
        SuperController superController = new SuperController();

        try {
            superController.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
