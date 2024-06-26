![7820d545-21f8-463d-9852-f9d8c4fcf45f](https://github.com/gautamkshah/Shri-BhagwatGita/assets/109517113/7ae92f08-88e6-4eea-a093-68bad020bcc9)<!DOCTYPE html>
<html lang="en">
<head>
   
</head>
<body>


<h1>Bhagwatgita App</h1>
![image](https://github.com/gautamkshah/Shri-BhagwatGita/assets/109517113/975d1bfa-8c05-45d3-abb4-a1dccef2f870)

<h2>Introduction</h2>
<p>The Bhagwatgita app is designed to provide a comprehensive digital version of the Bhagwatgita, including all chapters and verses. The app allows users to read, search, and navigate through the sacred text with ease.</p>

<h2>Features</h2>
<ul>
    <li><strong>Chapter Navigation</strong>: Easily navigate through all chapters of the Bhagwatgita.</li>
    <li><strong>Verse Display</strong>: View all verses within each chapter.</li>
    <li><strong>Search Functionality</strong>: Search for specific verses or keywords.</li>
    <li><strong>Offline Access</strong>: Access the content offline using a local database.</li>
    <li><strong>User-Friendly UI</strong>: Clean and intuitive user interface for a seamless reading experience.</li>
</ul>

<h2>Architecture</h2>
<h3>API Calls</h3>
<p>The app interacts with a remote API to fetch the Bhagwatgita content. Here is a brief overview of the API endpoints used:</p>
<ul>
    <li><code>GET /chapters</code>: Fetches a list of all chapters.</li>
    <li><code>GET /chapters/{id}/verses</code>: Fetches all verses for a specific chapter.</li>
</ul>

<h3>Room Database</h3>
<p>The app uses the Room database to store data locally, enabling offline access. The following entities are defined:</p>

<h4>Chapter</h4>
<pre><code>@Entity(tableName = "chapters")
public class Chapter {
    @PrimaryKey
    public int id;
    public String title;
    public int verseCount;
}
</code></pre>

<h4>Verse</h4>
<pre><code>@Entity(tableName = "verses")
public class Verse {
    @PrimaryKey
    public int id;
    public int chapterId;
    public String text;
}
</code></pre>

<h2>Installation</h2>
<p>To set up the Bhagwatgita app locally, follow these steps:</p>
<ol>
    <li><strong>Clone the repository</strong>:
        <pre><code>git clone https://github.com/gautamkshah/Shri-BhagwatGita.git
cd bhagwatgita
        </code></pre>
    </li>
    <li><strong>Install dependencies</strong>:
        <pre><code>./gradlew build
        </code></pre>
    </li>
    <li><strong>Run the app</strong>:
        <pre><code>./gradlew installDebug
        </code></pre>
    </li>
</ol>

<h2>Usage</h2>
<p>Upon launching the app, you can navigate through the chapters and verses of the Bhagwatgita. Use the search bar to find specific verses or keywords.</p>

<h2>UI Design</h2>
<p>The app features a clean and intuitive user interface. Below are some screenshots showcasing the UI elements:</p>

<h3>Home Screen</h3>
![image](https://github.com/gautamkshah/Shri-BhagwatGita/assets/109517113/d2c7f467-683c-43b8-a3b3-a3bd09133d7f)

<h3>Chapter List</h3>
![image](https://github.com/gautamkshah/Shri-BhagwatGita/assets/109517113/768a6e74-ad1c-41f6-b34b-59ce2cdf3b6c)


<h3>Verse Display</h3>
![image](https://github.com/gautamkshah/Shri-BhagwatGita/assets/109517113/9f016a73-a5a0-43fb-baed-8e3aeef5bbc1)

<h3>Saved Chapters and verses</h3>
![image](https://github.com/gautamkshah/Shri-BhagwatGita/assets/109517113/b5964bd0-4cd4-4c47-b111-4edf83aabf07)

<h3>Splash Scrren></h3>
![image](https://github.com/gautamkshah/Shri-BhagwatGita/assets/109517113/5ce41c00-537e-4cb8-a2dc-803026bfb8a9)




<h2>Contributing</h2>
<p>We welcome contributions to enhance the Bhagwatgita app. Please follow these steps to contribute:</p>
<ol>
    <li><strong>Fork the repository</strong>.</li>
    <li><strong>Create a new branch</strong>:
        <pre><code>git checkout -b feature-branch
        </code></pre>
    </li>
    <li><strong>Make your changes</strong>.</li>
    <li><strong>Commit your changes</strong>:
        <pre><code>git commit -m "Add new feature"
        </code></pre>
    </li>
    <li><strong>Push to the branch</strong>:
        <pre><code>git push origin feature-branch
        </code></pre>
    </li>
    <li><strong>Create a Pull Request</strong>.</li>
</ol>

<h2>License</h2>
<p>This project is licensed under the MIT License. See the <a href="LICENSE">LICENSE</a> file for details.</p>

</body>
</html>



