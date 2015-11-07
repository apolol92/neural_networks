% Question-Vector
f = [1 0 0 1 0 0 0;1 0 0 0 0 0 1;1 0 1 0 0 1 0;1 0 0 0 1 0 0];
% Answer-Vector
a = [1 0 1 0 1 1 1;1 0 0 1 1 1 1;1 0 1 0 0 1 0;1 0 0 1 0 1 1];
% Create associative matrix
A = L_associative_creation(f',a);
% Create Test-Question
n_f = [1 1 1 0 0 1 0];
% Calculate Answer
process_assoziative_matrix(n_f,A)