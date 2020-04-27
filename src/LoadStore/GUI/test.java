package LoadStore.GUI;

import java.util.concurrent.TimeUnit;

public class test {

    public static void main(String[] args)
	{

        loadDataPage dataPage = new loadDataPage();

        while (!dataPage.checkDataLoaded()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
}

	
}
}