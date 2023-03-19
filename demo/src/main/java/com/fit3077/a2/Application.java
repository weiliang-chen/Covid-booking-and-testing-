package com.fit3077.a2;

import com.fit3077.a2.view.*;
import com.fit3077.a2.model.User;

import javax.swing.*;


public class Application
{
    private ProgressionUIState currentUIState = ProgressionUIState.LOGIN;
    private static String loginID;
    private static String bookingToEditID;
    private static String QRCode;
    private static boolean userHasStrongSymptoms;
    private User loginUser = null;
    private User modifiedUser = null;

//    private JFrame loginFrame = new CovidSystem("Covid System");
//    private JFrame mainMenuFrame = new MainMenu("Main Menu", this);
//    private JFrame searchFrame = new Search("Search Screen", this);
//    private JFrame viewBookingFrame = new ViewCovidBooking("Booking Viewing", this);
//    private JFrame questionnaireFrame = new Questionnaire("Questionnaire", this);
//    private JFrame onSiteBookingFrame = new OnSiteBooking("Covid Test Booking", this);
//    private JFrame onSiteTestingFrame = new OnSiteTesting("On Site Testing", this);
//    private JFrame pinCodeGenerationFrame = new PinCodeGeneration("Generated PIN Code", this);
//    private JFrame qrurlGenerationFrame = new QRURLGeneration("Generated QR & URL Code", this);
//    private JFrame qrurlScanningFrame = new QRURLScanning("QR Scanner", this);
//    private JFrame homeCovidTestFrame = new HomeCovidTest("Home Covid Test", this);

    public void UpdateProgressionUIState(ProgressionUIState progressionUIState)
    {
        switch(progressionUIState) {
            case LOGIN:
                LoadLoginScreen();
                break;
            case MAINMENU:
                LoadMainMenuScreen();
                break;
            case SEARCHING:
                LoadSearchScreen();
                break;
            case VIEWBOOKING:
                LoadViewBookingScreen();
                break;
            case QUESTIONARE:
                LoadQuestionnaireScreen();
                break;
            case COVIDBOOKING:
                LoadCovidBookingScreen();
                break;
            case ONSITETESTING:
                LoadOnSiteTestingScreen();
                break;
            case PINCODEGENERATION:
                LoadPinCodeGenerationScreen();
                break;
            case QRCODESCANNING:
                LoadQRURLScanningScreen();
                break;
            case QRURLGENERATION:
                LoadQRURLGenerationScreen();
                break;
            case HOMETESTING:
                LoadHomeTestingScreen();
                break;
            case MODIFYBOOKINGONSITE:
                LoadModifyBookingOnSite();
                break;
            case MODIFYBOOKINGPROMPT:
                LoadModifyBookingPrompt();
                break;
            case MODIFYBOOKINGPHONECALL:
                LoadModifyBookingPhoneCall();
                break;
            case MODIFYBOOKINGADJUSTMENT:
                LoadModifyBookingAdjustment();
                break;
            case ADMINBOOKINGEDITING:
                LoadAdminBookingEditing();
                break;
            case BOOKINGHISTORY:
                LoadBookingHistory();
                break;
            case SEARCHBOOKINGS:
                LoadSearchBookings();
                break;
        }
    }

    private void LoadLoginScreen()
    {
        JFrame frame = new CovidSystem("Covid System");
        frame.setVisible(true);
    }

    private void LoadMainMenuScreen()
    {
        JFrame frame = new MainMenu("Main Menu", this);
        frame.setVisible(true);
    }

    private void LoadSearchScreen()
    {
        JFrame frame = new Search("Search Screen", this);
        frame.setVisible(true);
    }

    private void LoadViewBookingScreen()
    {
        JFrame frame = new ViewCovidBooking("Booking Viewing", this);
        frame.setVisible(true);
    }

    private void LoadQuestionnaireScreen()
    {
        JFrame frame = new Questionnaire("Questionnaire", this);
        frame.setVisible(true);
    }

    private void LoadCovidBookingScreen()
    {
        JFrame frame = new OnSiteBooking("Covid Test Booking", this);
        frame.setVisible(true);
    }

    private void LoadOnSiteTestingScreen()
    {
        JFrame frame = new OnSiteTesting("On Site Testing", this);
        frame.setVisible(true);
    }

    private void LoadOnSiteBookingScreen()
    {
        JFrame frame = new OnSiteBooking("On Site Booking", this);
        frame.setVisible(true);
    }

    private void LoadPinCodeGenerationScreen()
    {
        JFrame frame = new PinCodeGeneration("Generated PIN Code", this);
        frame.setVisible(true);
    }

    private void LoadQRURLGenerationScreen()
    {
        JFrame frame = new QRURLGeneration("Generated QR & URL Code", this);
        frame.setVisible(true);
    }

    private void LoadQRURLScanningScreen()
    {
        JFrame frame = new QRURLScanning("QR Scanner", this);
        frame.setVisible(true);
    }

    private void LoadHomeTestingScreen()
    {
        JFrame frame = new HomeCovidTest("Home Covid Test", this);
        frame.setVisible(true);
    }

    private void LoadModifyBookingOnSite()
    {
        JFrame frame = new ModifyBookingOnSite("Modify Booking On-Site", this);
        frame.setVisible(true);
    }

    private void LoadModifyBookingPhoneCall()
    {
        JFrame frame = new ModifyBookingPhoneCall("Modify Booking Phone Call", this);
        frame.setVisible(true);
    }

    private void LoadModifyBookingPrompt()
    {
        JFrame frame = new ModifyBookingPrompt("ModifyBookingPrompt", this);
        frame.setVisible(true);
    }

    private void LoadModifyBookingAdjustment()
    {
        JFrame frame = new ModifyBookingAdjustment("Modify Booking Adjustment", this);
        frame.setVisible(true);
    }

    private void LoadAdminBookingEditing()
    {
        JFrame frame = new AdminEditBookings("Admin Booking Editing", this);
        frame.setVisible(true);
    }

    private void LoadBookingHistory()
    {
        JFrame frame = new BookingHistory("Booking History", this);
        frame.setVisible(true);
    }

    private void LoadSearchBookings()
    {
        JFrame frame = new SearchBookings("Search Bookings", this);
        frame.setVisible(true);
    }

    private void InitializeAllFrames()
    {

    }

    public void SetLoginID(String newLoginID)
    {
        loginID = newLoginID;
    }

    public String GetLoginID() { return loginID;}

    public String GetBookingToEditID() {return bookingToEditID;}

    public void SetBookingToEditID(String newBookingToEditID) {bookingToEditID = newBookingToEditID;}

    public void SetQRCode(String newQRCode)
    {
        QRCode = newQRCode;
    }

    public String GetQRCode() { return QRCode;}

    public boolean GetUserHasStrongSymptoms() { return userHasStrongSymptoms;}

    public void SetHasStrongSymptoms(boolean newHasStrongSymptoms)
    {
        userHasStrongSymptoms = newHasStrongSymptoms;
    }

    public User GetLoginUser() {
        return loginUser;
    }

    public void SetLoginUser(User newLoginUser) { loginUser = newLoginUser;}

    public User GetModified() {
        return modifiedUser;
    }

    public void SetModifiedUser(User newModifiedUser) { modifiedUser = newModifiedUser;}

    public static void main(String[] args)
    {
        Application controller = new Application();
        controller.UpdateProgressionUIState(ProgressionUIState.LOGIN);
    }
}
