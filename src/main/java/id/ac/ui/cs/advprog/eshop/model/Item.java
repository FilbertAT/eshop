package id.ac.ui.cs.advprog.eshop.model;

import com.fasterxml.jackson.databind.util.Named;

/**
* Base interface for all items in the shop
* Combines multiple specific interfaces
*/
public interface Item extends Identifiable, Named, Quantifiable {
    // No additional methods needed as we're composing from other interfaces
}