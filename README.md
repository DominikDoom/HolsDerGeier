# Hols der Geier
<p>
    Hols der Geier was realized in Java as part of the module Fundamentals of Programming. 
</p>

<p>
    <h3>How to create your own Bot</h3>
    Upon opening in your favorite IDE, you just need to extend your Bot Class with the Player Class and override the abstract method
    <br>
</p>

```java
public abstract PlayerCard getNextCardFromPlayer();
```

<p>
    <h5>Example (from Localplayer):</h5>
</p>

```java
@Override
public PlayerCard getNextCardFromPlayer() {
        try {
            System.out.print("Your Cards left to play: ");
            this.cards.forEach(k -> System.out.print(k.getValue() + " "));
            System.out.print("\nYour card: ");
            final PlayerCard pC =  new PlayerCard(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));
            return pC;
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
}
```
<p>
    <h3>Rules</h3>
    For rules click <a href = "https://de.wikipedia.org/wiki/Hol%E2%80%99s_der_Geier" ><b>here</b></a>!
</p>
        
<p>
    <h3>Upcoming changes</h3>
    <ul>
        <li>GUI Support</li>
        <li>Add more Bots (you can simply add your own and contribute to the project by cloning and create a Pull Request afterwards!)</li>
        <li>Better Exception handling (coding my own handler)</li>
        <li>Database setup to save stats</li>
        <li>Online game vs. other players in realtime</li>
        <li>Use a Logger instead of System.out</li>
    </ul>
</p>
<br>
<p>
    Thank you for contributing! <br>
</p>
    Kind regards,
<br>
    <b>rabitem</b>
