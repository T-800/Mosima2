fish() :-
    jpl_new('javax.swing.JFrame', ['frame with dialog'], F),
    write(F).
    %jpl_call(F, setVisible, [@(true)], _).
