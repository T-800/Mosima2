exploration(X,Y) :-
	jpl_call("FollowBehaviour",explor,[X,Y],R),
	jpl_is_true(R).


interessant(X, Y) :-
    jpl_call("sma.SmartAgent",isAltSupAvg,[X, Y],R),
    jpl_is_true(R).