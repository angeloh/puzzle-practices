#Maintaining.pl

@my_str_array = ('orange', 'burgundy', 'mustard', 'white', 'green', 'red', 'pink');
my $prevStr = "";
foreach $str (@my_str_array) {
	print "$str" . "$prevStr\n";
	$prevStr = $str . $prevStr;
}