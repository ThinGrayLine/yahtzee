selectDice.setOnClick(e -> {
	// works only for the 5 dice
	if (turn == 3) {
		// stop event handling this
	}

	if (hand.selectDice() == true) { // get(i)?
		hand.selectDice() == false;
	} else {
		hand.selectDice() == true;
	}
});

// checks for combination event handler
isCombination.setOnClick(e -> {
	// checks if it is available (if-else with css properties: red for yes, black for no)
	// if true, activate selectCombination eventhandler
});

// select combination event handler
selectCombination.setOnClick(e -> {
	// change css properties and remove from combo list
	// alter player score
	// end turn
	
});



