# Hols der Geier
<p>
    Hols der Geier was realized in Java as part of the module Fundamentals of Programming. 
</p>

<p>
    <h4>How to create your own Bot</h4>
    You just need to extends the Player Class and Override the abstract method
    <br>
</p>
    <code>java public abstract PlayerCard getNextCard();</code>
<p>
    <h5>Example (from Localplayer):</h5>
</p>
    <code>java   @Override <br>
                   public PlayerCard getNextCard() { <br>
                       try { <br>
                           System.out.print("Your Cards left to play: "); <br>
                           this.cards.forEach(k -> System.out.print(k.getValue() + " ")); <br>
                           System.out.print("\nYour card: "); <br>
                           final PlayerCard pC =  new PlayerCard(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())); <br>
                           setLastMove(pC); <br>
                           remove(pC); <br>
                           return pC; <br>
                       }catch (IOException | IllegalMoveException e) { <br>
                           System.out.println(e.getMessage()); <br>
                       } <br>
                       return null; <br>
                   }<br></code>
<p>
    <br>
    Maybe I'll make it less complex to override so you don't need to call <code>java protected final void setLastMove(PlayerCard lastMove)</code> and
    <code>java protected final void remove(final PlayerCard c) throws IllegalMoveException</code> on your own.
</p>
<br>
<br>
<p>
    For rules click <a href = "https://de.wikipedia.org/wiki/Hol%E2%80%99s_der_Geier" ><b>here</b></a>!
</p>
<p>
    <h4>Upcoming changes:</h4>
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
