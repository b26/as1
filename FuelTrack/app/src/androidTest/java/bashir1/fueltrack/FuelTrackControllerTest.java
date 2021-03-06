package bashir1.fueltrack;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by X on 16-01-29.
 */
public class FuelTrackControllerTest extends ActivityInstrumentationTestCase2 {
    int pass = 1;
    private FuelTrackController fc = FuelTrackApplication.getController();
    Context context;
    Entry entry;

    public FuelTrackControllerTest() {
        super(MainActivity.class);
    }

    /* tests add (Entry entry, int index) */
    public void testAddEntry() {
        /* create a new entry*/

        entry = new Entry(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        fc.add(entry, 0);
        /* check if entry exists */
        assertTrue(fc.hasEntry(entry));
    }

    /* tests add(Date date, String station...) */
    public void testAddEntryLong() {
        boolean result = fc.add(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        assertTrue(result);
    }

    public void testAddShort() {
        entry = new Entry(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        assertTrue(fc.add(entry));
    }


    public void testSave() {
        context = FuelTrackApplication.getContext();
        int save_result;
        entry = new Entry(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        fc.add(entry);
        save_result = fc.save(context);
        assertEquals(pass, save_result);
    }

    public void testLoad() {
        int load_result;
        context = FuelTrackApplication.getContext();
        load_result = fc.load(context);
        assertEquals(pass, load_result);
    }

    public void testNewEntry() {
        Entry entry = fc.newEntry(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        assertNotNull(entry);
    }


    public void testGetEntryAtIndex() {
        entry = new Entry(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        fc.add(entry, 0);
        assertEquals(entry, fc.getAtIndex(0));
    }

    public void testInit() {
        assertTrue(fc.init());
    }

    public void testHasEntry() {
        entry = new Entry(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        fc.add(entry);
        assertTrue(fc.hasEntry(entry));
    }

    public void testValidateFalse() {
        context = FuelTrackApplication.getContext();
        EditText text = new EditText(context);
        boolean valid;
        text.setText("");
        valid = fc.validate(text, text, text, text, text, text);
        assertFalse(valid);
    }

    public void testValidateTrue() {
        context = FuelTrackApplication.getContext();
        EditText text = new EditText(context);
        boolean valid;
        text.setText("something");
        valid = fc.validate(text, text, text, text, text, text);
        assertTrue(valid);
    }

    public void testTotalCost() {
        /* create a new set of logs */

        fc.initLogs();
        assertEquals("No costs yet.", fc.totalCost());

        /* add new log and check the cost */
        entry = new Entry(new Date(), "Shell", 999.2, 45.2, 109.2, "Regular");
        fc.add(entry);


        assertEquals("Total Cost: $49.36", fc.totalCost());

    }


}
