# Hols der Geier
<p>
    Hols der Geier was realized in Java as part of the module Fundamentals of Programming. 
</p>

<p>
    <h3>How to create your own Bot</h3>
    You just need to extend the Player Class  and override the abstract method
    <br>
</p>

```java
public abstract PlayerCard getNextCard();
```

<p>
    <h5>Example (from Localplayer):</h5>
</p>

```java
@Override
public PlayerCard getNextCard() {
    try {
        System.out.print("Your Cards left to play: ");
        this.cards.forEach(k -> System.out.print(k.getValue() + " "));      // get available cards and print it for the user
        System.out.print("\nYour card: ");                                  // formating reasons
        final PlayerCard pC =  
            new PlayerCard(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));
        setLastMove(pC);                                                    // sets it to the lastMove
        remove(pC);                                                         // remove card from playableCards
        return pC;                                                          // return the PlayerCard
    }catch (IOException | IllegalMoveException e) {                         // catch Exceptions
        System.out.println(e.getMessage());
    }
    return null;
}
```

<p>
    <br>
    Maybe I'll make it less complex to get a Bot work so you don't need to override <code>protected final void setLastMove(PlayerCard lastMove)</code> and <code>protected final void remove(final PlayerCard c) throws IllegalMoveException</code> each time on your own.
</p>

<p>
    <h3>Rules</h3>
    For rules click <a href = "https://de.wikipedia.org/wiki/Hol%E2%80%99s_der_Geier" ><b>here</b></a>!
</p>
        
<p>
    <h3>Upcoming changes:</h3>
    <ul>
        <li>GUI Support</li>
        <li>Add more Bots (you can simply add your own and contribute to the project by cloning and create a Pull Request afterwards!)</li>
        <li>Better Exception handling (coding my own handler)</li>
        <li>Database setup to save stats</li>
        <li>Online game vs. other players in realtime</li>
    </ul>
</p>
<br>
<p>
    Thank you for contributing! <br>
</p>
    Kind regards,
<br>
    <b>rabitem</b>
