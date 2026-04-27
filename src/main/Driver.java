package main;

import controllers.NewsFeed;
import models.EventPost;
import models.MessagePost;
import models.PhotoPost;
import models.Post;
import utils.ScannerInput;

public class Driver {

    private final NewsFeed newsFeed = new NewsFeed();

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.runMenu();
    }

    private int mainMenu() {
        System.out.println("Social Network Menu");
        System.out.println("-----------------------");
        System.out.println("1) Add a Post");
        System.out.println("2) Update a Post");
        System.out.println("3) Delete a Post");
        System.out.println("4) List Posts");
        System.out.println("5) Like / Unlike Posts");
        System.out.println("-----------------------");
        System.out.println("6) Save Posts");
        System.out.println("7) Load Posts");
        System.out.println("-----------------------");
        System.out.println("0) Exit");
        return ScannerInput.readNextInt("==>> ");
    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {
            switch (option) {
                case 1:
                    addPost();
                    break;
                case 2:
                    updatePost();
                    break;
                case 3:
                    deletePost();
                    break;
                case 4:
                    viewPosts();
                    break;
                case 5:
                    likeUnlikePosts();
                    break;
                case 6:
                    savePosts();
                    break;
                case 7:
                    loadPosts();
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }

        System.out.println("Exiting...bye");
    }


    //------------------------------------------------------------------------------------------
    //  Option 1 - Add Posts - the user is asked if it is a message or a photo post
    //             and the required details are then gathered before adding the specific object
    //------------------------------------------------------------------------------------------
    private void addPost(){

        boolean isAdded = false;

        System.out.println("---------------------------");
        System.out.println("1) Add a Message Post");
        System.out.println("2) Add a Photo Post");
        System.out.println("3) Add an Event Post");
        System.out.println("---------------------------");
        int option = ScannerInput.readNextInt("==>> ");

        switch (option) {
            case 1:
            {
                String authorName = ScannerInput.readNextLine("Enter the Author Name:  ");
                String message = ScannerInput.readNextLine("Enter the Message:  ");
                isAdded = newsFeed.addPost(new MessagePost(authorName, message));
            }
                break;
            case 2:
            {
                String authorName = ScannerInput.readNextLine("Enter the Author Name:  ");
                String caption = ScannerInput.readNextLine("Enter the Caption:  ");
                String filename = ScannerInput.readNextLine("Enter the Filename:  ");
                isAdded = newsFeed.addPost(new PhotoPost(authorName, caption, filename));
            }
                break;
            case 3:
            {
                String authorName = ScannerInput.readNextLine("Enter the Author Name:  ");
                String eventName = ScannerInput.readNextLine("Enter the Event Name:  ");
                double eventCost = ScannerInput.readNextDouble("Enter the Event Cost:  ");
                isAdded = newsFeed.addPost(new EventPost(authorName, eventName, eventCost));
            }
                break;
            default:
                System.out.println("Invalid option entered: " + option);
                break;
        }

        if (isAdded){
            System.out.println("Post Added Successfully");
        }
        else{
            System.out.println("No Post Added");
        }
    }


    //------------------------------------------------------------------------------------------
    //  Option 2 - Update Posts - if posts exist, the user is asked if it is a message or a photo post
    //             and the required details are then gathered before adding the specific object
    //------------------------------------------------------------------------------------------
    private void updatePost() {

        if (newsFeed.numberOfPosts() > 0) {
            boolean isUpdated = false;

            System.out.println("---------------------------");
            System.out.println("1) Update a Message Post");
            System.out.println("2) Update a Photo Post");
            System.out.println("3) Update an Event Post");
            System.out.println("---------------------------");
            int option = ScannerInput.readNextInt("==>> ");

            switch (option) {
                case 1:
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a MessagePost,
                    //gather the new data from the user and update the selected object.
                    showMessagePosts();
                    if (newsFeed.numberOfMessagePosts() > 0) {
                        int messageIndex = ScannerInput.readNextInt("Enter the index of the message to update ==> ");
                        if (newsFeed.isValidMessagePostIndex(messageIndex)) {
                            String author = ScannerInput.readNextLine("Enter the Author Name:  ");
                            String message = ScannerInput.readNextLine("Enter the Message:  ");
                            //pass the index of the product and the new product details to Store for updating and check for success.
                            isUpdated = newsFeed.updateMessagePost(messageIndex, author, message);
                        }
                    }
                    break;
                case 2:
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a PhotoPost,
                    //gather the new data from the user and update the selected object.
                    showPhotoPosts();
                    if (newsFeed.numberOfPhotoPosts() > 0) {
                        int photoIndex = ScannerInput.readNextInt("Enter the index of the photo post to update ==> ");
                        if (newsFeed.isValidPhotoPostIndex(photoIndex)) {
                            String author = ScannerInput.readNextLine("Enter the Author Name:  ");
                            String caption = ScannerInput.readNextLine("Enter the Caption:  ");
                            String filename = ScannerInput.readNextLine("Enter the Filename:  ");
                            isUpdated = newsFeed.updatePhotoPost(photoIndex, author, caption, filename);
                        }
                    }
                    break;
                case 3:
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a PhotoPost,
                    //gather the new data from the user and update the selected object.
                    showEventPosts();
                    if (newsFeed.numberOfEventPosts() > 0) {
                        int eventIndex = ScannerInput.readNextInt("Enter the index of the event post to update ==> ");
                        if (newsFeed.isValidEventPostIndex(eventIndex)) {
                            String author = ScannerInput.readNextLine("Enter the Author Name:  ");
                            String eventName = ScannerInput.readNextLine("Enter the Event Name:  ");
                            double eventCost = ScannerInput.readNextDouble("Enter the Event Cost:  ");
                            isUpdated = newsFeed.updateEventPost(eventIndex, author, eventName, eventCost);
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            if (isUpdated) {
                System.out.println("Post Updated Successfully");
            } else {
                System.out.println("No Post Updated");
            }
        }
        else{
                System.out.println("No posts added yet");
            }
    }


    //------------------------------------------------------------------------------------------
    //  Option 3 - Delete Posts - if posts exist, print all posts and ask the user to input the index
    //             of the post they wish to delete.
    //------------------------------------------------------------------------------------------
    private void deletePost(){
        showPosts();
        if (newsFeed.numberOfPosts() > 0){
            //only ask the user to choose the message post to delete if posts exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the post to delete ==> ");
            //pass the index of the message post to NewsFeed for deleting and check for success.
            Post postToDelete = newsFeed.deletePost(indexToDelete);
            if (postToDelete != null){
                System.out.println("Delete Successful! Deleted post: " + postToDelete.display());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    //---------------------------------------------------------------------
    //  Option 4 - List Posts
    //---------------------------------------------------------------------

    //The user is asked if they want to view all posts, or just the messages or photos ones.
    private void viewPosts() {
        if (newsFeed.numberOfPosts() > 0) {
            System.out.println("---------------------------");
            System.out.println("1) View ALL Posts");
            System.out.println("2) View Message Posts");
            System.out.println("3) View Photo Posts");
            System.out.println("4) View Event Posts");
            System.out.println("---------------------------");
            int option = ScannerInput.readNextInt("==>> ");

            switch (option) {
                case 1:
                    showPosts();
                    break;
                case 2:
                    showMessagePosts();
                    break;
                case 3:
                    showPhotoPosts();
                    break;
                case 4:
                    showEventPosts();
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
        }
        else{
            System.out.println("Option Invalid - No posts stored");
        }
    }

    //print all the posts in newsfeed i.e. array list.
    private void showPosts(){
        System.out.println("List of All Posts are:");
        System.out.println(newsFeed.show());
    }

    //print the message posts in newsfeed i.e. array list.
    private void showMessagePosts(){
        System.out.println("List of Message Posts are:");
        System.out.println(newsFeed.showMessagePosts());
    }

    //print the photo posts in newsfeed i.e. array list.
    private void showPhotoPosts(){
        System.out.println("List of Photo Posts are:");
        System.out.println(newsFeed.showPhotoPosts());
    }

    //print the photo posts in newsfeed i.e. array list.
    private void showEventPosts(){
        System.out.println("List of Event Posts are:");
        System.out.println(newsFeed.showEventPosts());
    }

    //------------------------------------------------------------------------------------------
    //  Option 5 - Like / Unlike Posts - the user is asked if it is a message or a photo post
    //             and the required details are then gathered before adding the specific object
    //------------------------------------------------------------------------------------------
    private void likeUnlikePosts(){

        System.out.println("---------------------------");
        System.out.println("Do you want to...");
        System.out.println("1) Like a post");
        System.out.println("2) Unlike a post");
        System.out.println("---------------------------");
        int likeOption = ScannerInput.readNextInt("==>> ");

        switch (likeOption) {
            case 1:
            {
                showMessagePosts();
                showPhotoPosts();
                int index = ScannerInput.readNextInt("Enter the index of the post ==> ");
                newsFeed.likeAPost(index);
                System.out.println(newsFeed.findPost(index).display());
            }
                break;
            case 2:
            {
                showMessagePosts();
                showPhotoPosts();
                int index = ScannerInput.readNextInt("Enter the index of the post ==> ");
                newsFeed.unLikeAPost(index);
                System.out.println(newsFeed.findPost(index).display());
            }
                break;
            default:
                System.out.println("Invalid option entered: " + likeOption);
                break;
        }

    }


    //---------------------------------------------------------------------
    //  Options 6 and 7 - Save and Load Posts
    //---------------------------------------------------------------------

    //save all the posts in the newsFeed to a file on the hard disk
    private void savePosts() {
        try {
            newsFeed.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    //load all the posts into the newsFeed from a file on the hard disk
    private void loadPosts() {
        try {
            newsFeed.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

}
