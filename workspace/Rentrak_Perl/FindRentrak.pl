# FindRentrak.pl

# grap the input file
$inputFile = $ARGV[0];
# open file
open(FILE, $inputFile) or die("Unable to open file ".$inputFile);
# read file into an array
@data = <FILE>;
close(FILE);
my $count = 0;
# then loop through the file...
foreach $data (@data){
	$data = lc $data;
	$count++ while $data =~ /rentrak/g;	
} #end foreach

print "There are $count \"Rentrak\" in the string";