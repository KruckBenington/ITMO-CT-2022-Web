package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Color;
import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", Color.GREEN),
            new User(6, "pashka", "Pavel Mavrin", Color.BLUE),
            new User(9, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", Color.RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "This new old house", "We bought an old house, my boyfriend and I. He’s in charge of the “new” construction – converting the kitchen in to the master bedroom for instance, while I’m on wallpaper removal duty. The previous owner papered EVERY wall and CEILING! Removing it is brutal, but oddly satisfying. The best feeling is getting a long peel, similar to your skin when you’re peeling from a sunburn. I don’t know about you but I kinda make a game of peeling, on the hunt for the longest piece before it rips.Under a corner section of paper in every room is a person’s name and a date. Curiosity got the best of me one night when I Googled one of the names and discovered the person was actually a missing person, the missing date matching the date under the wallpaper! The next day, I made a list of all the names and dates. Sure enough each name was for a missing person with dates to match. We notified the police who naturally sent out the crime scene team. I overhead one tech say “yup, it’s human.” Human? What’s human? “Ma’am, where is the material you removed from the walls already? This isn’t wallpaper you were removing.", 6),
            new Post(3, "I hate it when my brother Charlie has to go away", "I hate it when my brother Charlie has to go away. My parents constantly try to explain to me how sick he is. That I am lucky for having a brain where all the chemicals flow properly to their destinations like undammed rivers. When I complain about how bored I am without a little brother to play with, they try to make me feel bad by pointing out that his boredom likely far surpasses mine, considering his confine to a dark room in an institution. I always beg for them to give him one last chance. Of course, they did at first. Charlie has been back home several times, each shorter in duration than the last. Every time without fail, it all starts again. The neighbourhood cats with gouged out eyes showing up in his toy chest, my dad’s razors found dropped on the baby slide in the park across the street, mom’s vitamins replaced by bits of dishwasher tablets. My parents are hesitant now, using “last chances” sparingly. They say his disorder makes him charming, makes it easy for him to fake normalcy, and to trick the doctors who care for him into thinking he is ready for rehabilitation. That I will just have to put up with my boredom if it means staying safe from him. I hate it when Charlie has to go away. It makes me have to pretend to be good until he is back.", 1),
            new Post(7, "My Daughter Learned to Count", "My daughter woke me around 11:50 last night. My wife and I had picked her up from her friend Sally’s birthday party, brought her home, and put her to bed. My wife went into the bedroom to read while I fell asleep watching the Braves game.”Daddy,” she whispered, tugging my shirt sleeve. “Guess how old I’m going to be next month.””I don’t know, beauty,” I said as I slipped on my glasses. “How old?”She smiled and held up four fingers.It is 7:30 now. My wife and I have been up with her for almost 8 hours. She still refuses to tell us where she got them.", 11),
            new Post(23, "He Stood Against My Window", "I don’t know why I looked up, but when I did I saw him there. He stood against my window. His forehead rested against the glass, and his eyes were still and light and he smiled a lipstick-red, cartoonish grin. And he just stood there in the window. My wife was upstairs sleeping, my son was in his crib and I couldn’t move I froze and watched him looking past me through the glass.Oh, please no. His smile never moved but he put a hand up and slid it down the glass, watching me. With matted hair and yellow skin and face through the window.I couldn’t do anything. I just stayed there, frozen, feet still in the bushes I was pruning, looking into my home. He stood against my window.", 11)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
