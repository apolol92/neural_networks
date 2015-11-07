function [ output_args ] = process_assoziative_matrix( f, M )
%PROCESS_ASSOZIATIVE_MATRIX Calulate the answer of a given question f
%   f is the question
%   M is the associative matrix

% This matrix will hold the result of the process
result = f*M;
% Get the threshold
threshold = max(result);
% Get the matrix size
matrix_size = size(result);
% Filter the column size
max_cols = matrix_size(2);
% Iterate throw the matrix
for c = 1:max_cols
    % if the current value is equal to the threshold
    if result(1,c) == threshold
        result(1,c) = 1;
    else
        result(1,c) = 0;
    end
end
% Set output should be result
output_args = result;
end

