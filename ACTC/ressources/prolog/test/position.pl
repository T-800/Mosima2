use_module(library(jpl)).


%initial state
joueur(player1).
joueur(player2).
envie(player1). % etre en vie
envie(player2).
%blesser(X).
%blesser(Y).


observeAgents(X):-
	alive(X),
	not(blesser(X)),
	jpl_call('SmartAgent',observeAgent,[],@void),
	jpl_call('SmartAgent',observeAgentVerification,[],R),%Verifier que bien observeAgent action a ete faite
	jpl_is_true(R).


observeEnvironnment(X, Y):-
	alive(X),
	not(blesser(X)),
	not(ennemiEnvu(Y)),
	jpl_call('SmartAgent',observeEnvironnement,[],@void),
	jpl_call('SmartAgent',observeEnvironnementVerification,[],R),%Verifier que bien observeAgent action a ete faite
	jpl_is_true(R).


victorious(X,Y) :- 
	alive(X),
	not(alive(Y)),
	jpl_call('ObserveBehaviour',victoryVerification,[],R),%Verifier que bien observeAgent action a ete faite
	jpl_is_true(R).

tirer(X,Y) :-
	alive(X),
	not(blesser(X)),
	ennemiEnvu(Y),
	jpl_call('ShootBehaviour',shoot,[Y],@void),
	jpl_call('ShootBehaviour',shootedVerification,[Y],R),
	jpl_is_true(R).
	

pointInteret(X) :-
    jpl_call('java.lang.System',getProperty,['user.dir'],F),
  	lahauteur(X),
  	or(depth(X)),
    or(champDeVision(X)),
    jpl_call('PointDinteret',pointDinteret,[],@void),
    jpl_call('PointDinteret',pointDinteretVerification,[Y],R),
	jpl_is_true(R).	
	
fuite(X,Y) :-
	alive(X),
	alive(Y),
	tirer(Y,X),
	blesser(X),
    jpl_call('FuiteBehaviour',fuite,[],@void),
    jpl_call('FuiteBehaviour',fuiteVerification,[Y],R),
	jpl_is_true(R).	
	
follow(X,Y) :-
	random(X),
	observe(X,Y),
	not(tirer(X,Y)),
	not(tirer(Y,X)),
	not(blesser(X)),
	blesser(Y),
	jpl_call('FollowBehaviour',follow,[],@void),
	jpl_call('FollowBehaviour',follow,[],R),
	jpl_is_true(R).	
	
	
%cettefonction doit verifier qu'il n'y a pas l'ennemi en vue(je pense c deja code par le prof dans environnement
%la fonction que j'utilise pour scanner l'environnement dans shootBehaviour elle rend la liste des agents presents
% du coup faut une fonction qui l'appelle et si c vide, sa rend false dans EnnemiEnVu(prolog).	
ennemiEnvu(Y):-
	jpl_call('ObserveBehaviour',ObserveAgentBoolean,[],R),
	jpl_is_true(R).	

%J'ai besoin de verifier qu'il est blesse
blesse(X):-
	jpl_call('Environnement',blesser,[X],R),
	jpl_is_true(R).		
	
%Avoir un boolean pour verifier si oui ou non 
lahauteur() :-
    %jpl_call('java.lang.System',getProperty,['user.dir'],F),
    %write(F).
	jpl_call('sma.agents.SmartAgent',supAvgAlt,[],R),
	jpl_is_true(R).

%Verifier le Depth
depth() :-
	jpl_call('sma.agents.SmartAgent',supAvgMaxDepth,[],R),
	jpl_is_true(R).
	
champDeVision() :-
	jpl_call('sma.agents.SmartAgent',supAvgFov,[],R),
	jpl_is_true(R).