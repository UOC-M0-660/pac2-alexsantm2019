package edu.uoc.pac2.data

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by alex on 02/07/2020.
 */

object FirestoreBookData {

    private const val TAG = "FirestoreBookData"

    /// Adds example books to Firestore
    fun addBooksDataToFirestoreDatabase() {
        val firestoreDatabase = Firebase.firestore
        // Make sure you are using your own google-services.json
        Log.i(TAG, "Using firebase Project: ${firestoreDatabase.app.options.projectId}")
        // Insert each book to Firestore
        booksData.forEach { bookMap ->
            firestoreDatabase
                    .collection("books")
                    .document(bookMap["uid"].toString())
                    .set(bookMap)
                    .addOnSuccessListener {
                        Log.i(TAG, "Creating book document SUCCESS: ${bookMap["title"]}")
                    }
                    .addOnFailureListener {
                        Log.w(TAG, "Creating book document FAILURE: ${bookMap["title"]}")
                    }
                    .addOnCanceledListener {
                        Log.w(TAG, "Creating book document CANCELED: ${bookMap["title"]}")
                    }
                    .addOnCompleteListener {
                        Log.i(TAG, "Creating book document COMPLETED: ${bookMap["title"]}")
                    }
        }
    }

    private val booksData: List<HashMap<String, Any>> = listOf(
            hashMapOf(
                    "uid" to 1,
                    "author" to "Harper Lee",
                    "description" to "From Harper Lee comes a landmark new novel set two decades after her beloved Pulitzer Prize-winning masterpiece, To Kill a Mockingbird. Maycomb, Alabama. Twenty-six-year-old Jean Louise Finch--'Scout'--returns home from New York City to visit her aging father, Atticus. Set against the backdrop of the civil rights tensions and political turmoil that were transforming the South, Jean Louise's homecoming turns bittersweet when she learns disturbing truths about her close-knit family, the town and the people dearest to her. Memories from her childhood flood back, and her values and assumptions are thrown into doubt. Featuring many of the iconic characters from To Kill a Mockingbird, Go Set a Watchman perfectly captures a young woman, and a world, in a painful yet necessary transition out of the illusions of the past--a journey that can be guided only by one's conscience. Written in the mid-1950s, Go Set a Watchman imparts a fuller, richer understanding and appreciation of Harper Lee. Here is an unforgettable novel of wisdom, humanity, passion, humor and effortless precision--a profoundly affecting work of art that is both wonderfully evocative of another era and relevant to our own times. It not only confirms the enduring brilliance of To Kill a Mockingbird, but also serves as its essential companion, adding depth, context and new meaning to an American classic.",
                    "publicationDate" to "14/07/2015",
                    "title" to "Go set a watchman",
                    "urlImage" to "https://www.wired.com/wp-content/uploads/2015/07/go-set-a-watchman-582x890.jpg"
            ),
            hashMapOf(
                    "uid" to 2,
                    "author" to "Paula Hawkins",
                    "description" to "EVERY DAY THE SAME\n\nRachel takes the same commuter train every morning and night. Every day she rattles down the track, flashes past a stretch of cozy suburban homes, and stops at the signal that allows her to daily watch the same couple breakfasting on their deck. She’s even started to feel like she knows them. Jess and Jason, she calls them. Their life—as she sees it—is perfect. Not unlike the life she recently lost.\n\nUNTIL TODAY\n\nAnd then she sees something shocking. It’s only a minute until the train moves on, but it’s enough. Now everything’s changed. Unable to keep it to herself, Rachel goes to the police. But is she really as unreliable as they say? Soon she is deeply entangled not only in the investigation but in the lives of everyone involved. Has she done more harm than good?",
                    "publicationDate" to "13/01/2015",
                    "title" to "The girl on the train",
                    "urlImage" to "https://upload.wikimedia.org/wikipedia/en/3/34/The_Girl_on_The_Train.jpg"
            ),
            hashMapOf(
                    "uid" to 3,
                    "author" to "Kristin Hannah",
                    "description" to "In the quiet village of Carriveau, Vianne Mauriac says goodbye to her husband, Antoine, as he heads for the Front. She doesn’t believe that the Nazis will invade France...but invade they do, in droves of marching soldiers, in caravans of trucks and tanks, in planes that fill the skies and drop bombs upon the innocent. When France is overrun, Vianne is forced to take an enemy into her house, and suddenly her every move is watched; her life and her child’s life is at constant risk. Without food or money or hope, as danger escalates around her, she must make one terrible choice after another.\n\nVianne’s sister, Isabelle, is a rebellious eighteen-year-old girl, searching for purpose with all the reckless passion of youth. While thousands of Parisians march into the unknown terrors of war, she meets the compelling and mysterious Gäetan, a partisan who believes the French can fight the Nazis from within France, and she falls in love as only the young can...completely. When he betrays her, Isabelle races headlong into danger and joins the Resistance, never looking back or giving a thought to the real--and deadly--consequences.\n\nWith courage, grace and powerful insight, bestselling author Kristin Hannah takes her talented pen to the epic panorama of WWII and illuminates an intimate part of history seldom seen: the women’s war. The Nightingale tells the stories of two sisters, separated by years and experience, by ideals, passion and circumstance, each embarking on her own dangerous path toward survival, love, and freedom in German-occupied, war-torn France--a heartbreakingly beautiful novel that celebrates the resilience of the human spirit and the durability of women. It is a novel for everyone, a novel for a lifetime.",
                    "publicationDate" to "3/02/2015",
                    "title" to "The nightingale",
                    "urlImage" to "https://upload.wikimedia.org/wikipedia/en/b/b3/The_Nightingale_%282015_novel%29.jpg"
            ),
            hashMapOf(
                    "uid" to 4,
                    "author" to "Neil Gaiman",
                    "description" to "Trigger Warning explores the masks we all wear and the people we are beneath them to reveal our vulnerabilities and our truest selves. Here is a rich cornucopia of horror and ghosts stories, science fiction and fairy tales, fabulism and poetry that explore the realm of experience and emotion. In 'Adventure Story'--a thematic companion to The Ocean at the End of the Lane--Gaiman ponders death and the way people take their stories with them when they die. His social media experience 'A Calendar of Tales' are short takes inspired by replies to fan tweets about the months of the year--stories of pirates and the March winds, an igloo made of books, and a Mother's Day card that portends disturbances in the universe. Gaiman offers his own ingenious spin on Sherlock Holmes in his award-nominated mystery tale 'The Case of Death and Honey'. And 'Click-Clack the Rattlebag' explains the creaks and clatter we hear when we're all alone in the darkness.",
                    "publicationDate" to "3/02/2015",
                    "title" to "Trigger Warning: short fictions and disturbances",
                    "urlImage" to "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1415036119l/22522808.jpg"
            ),
            hashMapOf(
                    "uid" to 5,
                    "author" to "Pierce Brown",
                    "description" to "With shades of The Hunger Games, Ender’s Game, and Game of Thrones, debut author Pierce Brown’s genre-defying epic Red Rising hit the ground running and wasted no time becoming a sensation. Golden Son continues the stunning saga of Darrow, a rebel forged by tragedy, battling to lead his oppressed people to freedom from the overlords of a brutal elitist future built on lies. Now fully embedded among the Gold ruling class, Darrow continues his work to bring down Society from within. A life-or-death tale of vengeance with an unforgettable hero at its heart, Golden Son guarantees Pierce Brown’s continuing status as one of fiction’s most exciting new voices.",
                    "publicationDate" to "06/01/2015",
                    "title" to "Golden son",
                    "urlImage" to "https://hanabooktopia.com/wp-content/uploads/2019/02/goldeb-son.jpg"
            ),
            hashMapOf(
                    "uid" to 6,
                    "author" to "Dean Koontz",
                    "description" to "Two years after the cataclysmic events that sent him journeying into mystery, Odd Thomas, the intrepid fry cook who sees the dead and tries to help them, has traveled full circle, back to his beloved home town of Pico Mundo and the people he loves. He has come to save them-and perhaps humanity-from the full flowering of evil it is his destiny to confront, as he draws ever closer to the truth of the world and his place in it. Stronger, wiser than he started, and with the help of the friends he has made along the way, Odd prepares to confront the terrible forces arrayed against him and possibly to journey still farther, to his long-awaited reunion with his lost love, Stormy Llewellyn.",
                    "publicationDate" to "13/01/2015",
                    "title" to "Saint odd",
                    "urlImage" to "https://horrornovelreviews.files.wordpress.com/2015/02/saint-odd.jpeg"
            ),
            hashMapOf(
                    "uid" to 7,
                    "author" to "Mindy Kaling",
                    "description" to "In Why Not Me?, Kaling shares her ongoing journey to find contentment and excitement in her adult life, whether it’s falling in love at work, seeking new friendships in lonely places, attempting to be the first person in history to lose weight without any behavior modification whatsoever, or most important, believing that you have a place in Hollywood when you’re constantly reminded that no one looks like you.\n\nIn 'How to Look Spectacular: A Starlet’s Confessions,' Kaling gives her tongue-in-cheek secrets for surefire on-camera beauty, ('Your natural hair color may be appropriate for your skin tone, but this isn’t the land of appropriate–this is Hollywood, baby. Out here, a dark-skinned woman’s traditional hair color is honey blonde.') 'Player' tells the story of Kaling being seduced and dumped by a female friend in L.A. ('I had been replaced by a younger model. And now they had matching bangs.') In 'Unlikely Leading Lady', she muses on America’s fixation with the weight of actresses, (“Most women we see onscreen are either so thin that they’re walking clavicles or so huge that their only scenes involve them breaking furniture.”) And in 'Soup Snakes', Kaling spills some secrets on her relationship with her ex-boyfriend and close friend, B.J. Novak ('I will freely admit: my relationship with B.J. Novak is weird as hell.')\n\nMindy turns the anxieties, the glamour, and the celebrations of her second coming-of-age into a laugh-out-loud funny collection of essays that anyone who’s ever been at a turning point in their life or career can relate to. And those who’ve never been at a turning point can skip to the parts where she talks about meeting Bradley Cooper.",
                    "publicationDate" to "15/09/2015",
                    "title" to "Why not me?",
                    "urlImage" to "https://di2ponv0v5otw.cloudfront.net/posts/2018/11/29/5bffcd063c98444a56db2cf6/m_5bffeb55df0307fec3f35bb5.jpeg"
            ),
            hashMapOf(
                    "uid" to 8,
                    "author" to "Aziz Ansari, Eric Klinenberg",
                    "description" to "At some point, every one of us embarks on a journey to find love. We meet people, date, get into and out of relationships, all with the hope of finding someone with whom we share a deep connection. This seems standard now, but it’s wildly different from what people did even just decades ago. Single people today have more romantic options than at any point in human history. With technology, our abilities to connect with and sort through these options are staggering. So why are so many people frustrated?\n\nSome of our problems are unique to our time. 'Why did this guy just text me an emoji of a pizza?' 'Should I go out with this girl even though she listed Combos as one of her favorite snack foods? Combos?!' 'My girlfriend just got a message from some dude named Nathan. Who’s Nathan? Did he just send her a photo of his penis? Should I check just to be sure?'\n\nBut the transformation of our romantic lives can’t be explained by technology alone. In a short period of time, the whole culture of finding love has changed dramatically. A few decades ago, people would find a decent person who lived in their neighborhood. Their families would meet and, after deciding neither party seemed like a murderer, they would get married and soon have a kid, all by the time they were twenty-four. Today, people marry later than ever and spend years of their lives on a quest to find the perfect person, a soul mate.\n\nFor years, Aziz Ansari has been aiming his comic insight at modern romance, but for Modern Romance, the book, he decided he needed to take things to another level. He teamed up with NYU sociologist Eric Klinenberg and designed a massive research project, including hundreds of interviews and focus groups conducted everywhere from Tokyo to Buenos Aires to Wichita. They analyzed behavioral data and surveys and created their own online research forum on Reddit, which drew thousands of messages. They enlisted the world’s leading social scientists, including Andrew Cherlin, Eli Finkel, Helen Fisher, Sheena Iyengar, Barry Schwartz, Sherry Turkle, and Robb Willer. The result is unlike any social science or humor book we’ve seen before.\n\nIn Modern Romance, Ansari combines his irreverent humor with cutting-edge social science to give us an unforgettable tour of our new romantic world.",
                    "publicationDate" to "16/06/2015",
                    "title" to "Modern Romance",
                    "urlImage" to "https://www.libertybooks.com/image/catalog/MZ.jpg"
            ),
            hashMapOf(
                    "uid" to 9,
                    "author" to "John Hargrove, Howard Chua-Eoan",
                    "description" to "Over the course of two decades, John Hargrove worked with 20 different whales on two continents and at two of SeaWorld's U.S. facilities. For Hargrove, becoming an orca trainer fulfilled a childhood dream. However, as his experience with the whales deepened, Hargrove came to doubt that their needs could ever be met in captivity. When two fellow trainers were killed by orcas in marine parks, Hargrove decided that SeaWorld's wildly popular programs were both detrimental to the whales and ultimately unsafe for trainers.\n\nAfter leaving SeaWorld, Hargrove became one of the stars of the controversial documentary Blackfish. The outcry over the treatment of SeaWorld's orca has now expanded beyond the outlines sketched by the award-winning documentary, with Hargrove contributing his expertise to an advocacy movement that is convincing both federal and state governments to act.\n\nIn Beneath the Surface, Hargrove paints a compelling portrait of these highly intelligent and social creatures, including his favorite whales Takara and her mother Kasatka, two of the most dominant orcas in SeaWorld. And he includes vibrant descriptions of the lives of orcas in the wild, contrasting their freedom in the ocean with their lives in SeaWorld.\n\nHargrove's journey is one that humanity has just begun to take-toward the realization that the relationship between the human and animal worlds must be radically rethought.",
                    "publicationDate" to "24/03/2015",
                    "title" to "Beneath the surface: killer whales, seaworld, and the truth beyond blackfish",
                    "urlImage" to "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1426127539i/23014804._UY475_SS475_.jpg"
            )
    )
}