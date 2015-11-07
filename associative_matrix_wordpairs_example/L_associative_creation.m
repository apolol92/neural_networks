function [ output_args ] = L_associative_creation( f, a )
%L_ASSOCIATIVE_CREATION This function creates a associative matrix via
%L-Learningrule
%   f contains in every column a question
%   a contains in every row a answer

% Get the size of the f matrix
f_size = size(f);
% Get the size of the a matrix
a_size = size(a);
% Create a zero associative matrix
A = zeros(f_size(1),a_size(2));
% Current row of the a matrix
arow = 1;
% Iterate each column of the f matrix
for fcol = 1:f_size(2)
    % Select current f column
    current_f = f(1:f_size(1),fcol);
    % Select current a row
    current_a = a(arow,1:a_size(2));
    % Calulate f(questionmatrix) with a(answermatrix)
    fa = current_f*current_a;
    % Create better associative matrix
    A = A | fa;
    % Go to the next row of the a matrix
    arow=arow+1;
end
% Set output to associative matrix
output_args = A;
end

