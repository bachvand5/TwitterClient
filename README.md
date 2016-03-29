# CoderSchool - TwitterClient

A simple Twitter client that supports viewing a Twitter timeline and composing a new tweet.

Submitted by: Huynh, Thi Bach Van

Time spent: 15 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can sign in to Twitter using OAuth login (2 points)
* [ ] User can view the tweets from their home timeline
  * [x] User should be displayed the username, name, and body for each tweet (2 points)
  * [ ] User should be displayed the relative timestamp for each tweet "8m", "7h" (1 point)
  * [x] User can view more tweets as they scroll with infinite pagination (1 point)
* [ ] User can compose a new tweet
  * [x] User can click a “Compose” icon in the Action Bar on the top right (1 point)
  * [x] User can then enter a new tweet and post this to twitter (2 points)
  * [x] User is taken back to home timeline with new tweet visible in timeline (1 point)

The following advanced user stories are optional:

* [ ] Advanced: While composing a tweet, user can see a character counter with characters remaining for tweet out of 140 (1 point)
* [ ] Advanced: Links in tweets are clickable and will launch the web browser (see autolink) (1 point)
* [ ] Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh) (1 point)
* [ ] Advanced: User can open the twitter app offline and see last loaded tweets
  *  [ ] Tweets are persisted into sqlite and can be displayed from the local DB (2 points)
* [ ] Advanced: User can tap a tweet to display a "detailed" view of that tweet (2 points)
* [ ] Advanced: User can select "reply" from detail view to respond to a tweet (1 point)
* [ ] Advanced: Improve the user interface and theme the app to feel "twitter branded" (1 to 5 points)
* [ ] Bonus: User can see embedded image media within the tweet detail view (1 point)
* [ ] Bonus: User can watch embedded video within the tweet (1 point)
* [x] Bonus: Compose activity is replaced with a modal overlay (2 points)
* [ ] Bonus: Use Parcelable instead of Serializable using the popular Parceler library. (1 point)
* [ ] Bonus: Apply the popular Butterknife annotation library to reduce view boilerplate. (1 point)
* [x] Bonus: Leverage RecyclerView as a replacement for the ListView and ArrayAdapter for all lists of tweets. (2 points)
* [ ] Bonus: Move the "Compose" action to a FloatingActionButton instead of on the AppBar. (1 point)
* [ ] Bonus: Replace Picasso with Glide for more efficient image rendering. (1 point)

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<a href="https://picasaweb.google.com/lh/photo/yK2c2GWwghO7-aj2bSgBu9MTjNZETYmyPJy0liipFm0?feat=embedwebsite"><img src="https://lh3.googleusercontent.com/-Q6TMU4j9R00/VvnY8W6JhyI/AAAAAAAABSQ/BKAPPNScNAUxqqKKqj5hq25zP-b8QC4VgCCo/s800-Ic42/2016_03_28_23_25_44.gif" height="569" width="320" /></a>

GIF created with [AZ Screen Recorder] and [Zamzar](http://www.zamzar.com/).

## Notes

Describe any challenges encountered while building the app.

## License

    Copyright [2016] [Huynh, Thi Bach Van]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
