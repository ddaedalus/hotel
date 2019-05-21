# Hotel Board Game

It's about the popular board game "Hotel", that every child used to love playing back in the 2000s. 
(NTUA MediaLab Project 2019)

# ΣΚΟΠΟΣ ΤΗΣ ΕΡΓΑΣΙΑΣ
Σκοπό της παραπάνω εργασίας αποτελούσε η εξοικείωση με τις βασικές αρχές
του αντικειμενοστραφούς προγραμματισμού με την χρήση της Java για την
υλοποίηση του αγαπημένου κλασικού επιτραπέζιου παιχνιδιού, του Hotel.
# ΥΛΟΠΟΙΗΣΗ ΤΗΣ ΕΡΓΑΣΙΑΣ
Βασική ιδέα για την υλοποίηση του έργου αποτέλεσε η κατασκευή των
βασικών κλάσεων για την δημιουργία των αντικειμένων του παίκτη (class
Player), του ταμπλό (class Board), των καρτών των ξενοδοχείων (class
HotelCard) και της βασικής κλάσης που θα περιλαμβάνει την main(). (class
Hotel).
Ακόμη χρειάστηκαν και άλλες κλάσεις που συνέβαλαν στην διευκόλυνση του
έργου: class Entrance (για τις εισόδους), class Dice (για το κανονικό ζάρι),
class BuildDice (για το ζάρι χτισίματος) και class Tuple (μια τούπλα που
περιλαμβάνει δυο int μεταβλητές).
Η βασική κλάση είναι η class Player, η οποία έχει όλες τις λειτουργίες του
παιχνιδιού, δηλαδή την μετακίνηση του παίκτη (move()), την αγορά
ξενοδοχείου (buyHotel()), την αγορά εισόδου (buyEntrance()), το χτίσιμο
ξενοδοχείου (build()), την χρεοκοπία του παίκτη (bankrupt()), καθώς και άλλες
μεθόδους, πέρα απο getters/setters, που θεωρήθηκαν χρίσιμες για την
υλοποίηση των παραπάνω λειτουργιών. Από την άλλη, οι υπόλοιπες κλάσεις,
πέρα από την class Hotel που περιέχει την main(), χρησιμοποιήθηκαν κατά
κύριο λόγο ως υποστήριξη της Player για την υλοποιήση των λειτουργιών της.
# Περιορισμοί
Δεν έχει υλοποιήθει το γραφικό περιβάλλον, παρά μόνο σε πολύ αρχικά στάδια
χωρίς να υποστηρίζει καμία λειτουργικότητα του παιχνιδιού. Ωστόσο, το
παιχνίδι έχει υλοποιηθεί σε επίπεδο terminal λαμβάνοντας μεγάλη
αλληλεπίδραση με τον χρήστη.
# Επίπλεον Λειτουργικότητες
Παρότι δεν έχει υλοποιηθεί το γραφικό περιβάλλον σε ικανοποιητικό επίπεδο
λόγω εξαιρετικά περιορισμένων χρονικών ορίων, εχούν υλοποιηθεί ορισμένες
επιπλέον λειτουργικότητες και έχουν ληφθεί παραδοχές για την βελτίωση του
παιχνιδιού που θα αναλυθουν παρακάτω.
• Δημιουργία γύρων (rounds) σε αντιστοίχηση με το χρόνο που τρέχει στο
γραφικό περιβάλλον
• Σε κάθε γύρο εκτυπώνεται η τρέχουσα κατάσταση του κόσμου με την
διαδρομή που μπορεί να ακολουθήσει ο παίκτης σε κόκκινο χρώμα, σε
αντιστοιχία με την εμφάνιση του ταμπλό στο γραφικό περιβάλλον.
• Αντιστοίχηση κάθε παίκτη με ένα χρώμα, ο player1: yelllow, ο player2:
green, o player3: blue και αναπαράσταση του κατά την εκτύπωση του
κόσμου.
• Δημιουργία διαφορετικού συμβόλου στον κόσμο για την αναπαράσταση
θέσης με δεσμευμένη είσοδο. (‘e’)
• Χρήση των παραπάνω χρωμάτων για την εκτύπωση των μηνυμάτων του
συστήματος με προφανώς διαφορετικό χρώμα ανάλογα με το ποιος
παίκτης έχει σειρά να παίξει.
• Χρήση κατάλληλων μηνυμάτων σε περίπτωση μη αναμενόμενων εισόδων
από τον χρήστη με σκοπό την αμέση διόρθωσή τους απο αυτόν.
• Συνεχής παρουσιάση των δεδομένων του παιχνιδιού στην αρχή του κάθε
γύρου παράλληλα με την εκτύπωση του κόσμου.
# Παραδοχές προς βελτίωση του παιχνιδιού
•Μετά από την κίνηση του εκάστοτε παίκτη λαμβάνεται υπόψιν η εξής
προτεραιότητα λειτουργιών όπου αυτές επιτρέπονται:
  - Παίρνω χρήματα από τράπεζα,
  - Πληρώνω τον αντίπαλο μου,
  - Αγοράζω είσοδο ή χτίζω ή αγοράζω ξενοδοχείο.
  
•Ο παίκτης επιλέγει που θα βάλει την είσοδο και όχι το παιχνίδι με τυχαιό
τρόπο.
• Χρησιμοποιείται μόνο το default παιχνίδι και όχι το simple, αφού εκείνο
περιέχει το αληθινό ταμπλό και τα ξενοδοχεία που περιείχε το κλασικό
επιτραπέζιο.
• Σε περίπτωση ισοβαθμίας ως προς την θέση ο παίκτης πηγαίνει στο
αμέσως διαθέσιμο διπλανό τετράγωνο. Έχει ληφθεί υπόψιν και η
περίπτωση και δεύτερης διαδοχικής ισοβαθμίας θέσης από άλλον
παίκτη.
• Κάθε ξενοδοχείο περιέχει ένα rank που σημαίνει το επιπέδο χτισίματος
που έχει υποστεί. Σε περίπτωση καθόλου χτισίματος το rank = -1 και
αυξάνει πάντοτε κατά 1 κάθε φορά που αναβαθμίζεται από τον χρήστη.
• Στην επιλογή της εισόδου από τον χρήστη ζητείται η επίλογη σειράς και
στήλης θεωρώντας ότι η αρίθμηση τους αρχίζει απο το 0 και όχι από το 1.
• Κάθε φορά που κάποιος χρήστης περάσει απο την τράπεζα του δίνεται η
δυνατότητα να λάβει τα 1000mls ή να τα αρνηθεί.
• Παιρνω την παραδοχη οτι αν η κανονικη αρχικη τιμη που εχει να
πληρωσει ξεπερνα τα mls του τοτε δεν τον αφηνει το παιχνιδι να ριξει
ζάρι, μολονότι υπάρχει πιθανότητα να μην πληρώσει καθόλου.
• Σε περίπτωση που ο παίκτης πέσει σε ‘Ε’, τότε αρχίκα του ζητάει το
παιχνίδι αν θέλει να αγοράσει μια είσοδο. Αν ο χρήστης απαντήσει με
“no”, τότε του ζητάει αν θέλει να χτίσει/αναβαθμίσει ξενοδοχείο αν
διαθέτει κάποιο. Αν δεν διαθέτει δεν θα του ζητήσει καν αν θέλει να
αγοράσει κάποια είσοδο.
# Τέλος, χρησιμοποιήθηκε η παρακάτω έκδοση της Java και του Javac:
openjdk version "11.0.1" 2018-10-16
OpenJDK Runtime Environment (build 11.0.1+13-Ubuntu-3ubuntu3.18.10.1)
OpenJDK 64-Bit Server VM (build 11.0.1+13-Ubuntu-3ubuntu3.18.10.1, mixed mode, sharing)



















































