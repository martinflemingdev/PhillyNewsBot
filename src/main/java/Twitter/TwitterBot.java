package Twitter;

import java.time.LocalTime;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterBot {
 
    public static void main(String[] args){

		while (true) {
			if (LocalTime.now().getMinute() == 4 &&
				LocalTime.now().getSecond() == 0 &&
				LocalTime.now().getNano() > 990000000 )
			{
				NewsService news = new NewsService();
				NewsObject todaysNews = news.getNews();
				
				String title = "";
				title = todaysNews.getArticles()[0].getTitle();
				
				String url = "";
				url = todaysNews.getArticles()[0].getUrl();
				
				newTweet(title + "\n" + url);
				
			}
		}
        
    }
    
    public static void newTweet(String tweet) {
        Twitter twitter = TwitterFactory.getSingleton();
        try {
            Status status = twitter.updateStatus(tweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}