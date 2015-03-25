package nce_client;

public class MainController
{
    private NetworkController networkController;
    
    public MainController()
    {
        Frame mainApplicationFrame = new Frame(this);
        
        //Connect to the server.
        getNetworkController().connectToServer();
    }
    
    public NetworkController getNetworkController()
    {
        if (networkController == null)
        {
            networkController = new NetworkController();
        }
        
        return networkController;
    }
}
