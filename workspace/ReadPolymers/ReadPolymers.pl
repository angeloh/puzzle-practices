$file = $ARGV[0] ; # the Polumers input file
#$file = "dna.input";
$output = 'dna.output' ; # the output file
open(POLYMERS, "<$file") or die("Cannot open file '$file' for reading\n"); # Open the input file
open(OUTP, ">$output") or die("Cannot open file '$output' for writing\n"); # Open the output file
@lines = <POLYMERS> ; # Read it into an array
close(POLYMERS) ; # Close the file

my $fileSize = @lines;
my %nucles; # the hash for storing current polumers' melting temperature

# read one lines each time
for (my $lineCount = 0; $lineCount < $fileSize; $lineCount++) {
	 my $wildCard = 0; # to calculate the number of wild-card
	 my $charIndex = 0; # how many character has been read in one line
	 %nucles = ();	 
	 my $curPoly = @lines[$lineCount];	
	 chomp $curPoly;
	 
	 my @chars_CurPoly = split("", $curPoly);
	 
	 my $curPolySize = @chars_CurPoly;
	 
	 # read each charater for one line
	 foreach my $adenine (@chars_CurPoly) {	 	 	
	 	$charIndex++;
	 	
	 	if ($adenine eq "R") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "a";
	 			$nucles{$tmpStr1} = 2;
	 			my $tmpStr2 = "g";
	 			$nucles{$tmpStr2} = 4;
	 		} else {	 			
	 		  foreach my $str (keys %nucles) {
	 		  	
	 			my $tmpIndex  = $charIndex - 1;
	 			
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;
	 				
	 				my $tmpStr2 = $str . "g";
	 				$nucles{$tmpStr2} = $curTemp + 4; 			
	 			} 		
	 		  }
	 		}	 	
	 	} 
	 	elsif ($adenine eq "Y") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "c";
	 			$nucles{$tmpStr1} = 4;
	 			my $tmpStr2 = "t";
	 			$nucles{$tmpStr2} = 2;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "c";
	 				$nucles{$tmpStr1} = $curTemp + 4;
	 				my $tmpStr2 = $str . "t";
	 				$nucles{$tmpStr2} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}	 	
	 	}
	 	elsif ($adenine eq "S") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "c";
	 			$nucles{$tmpStr1} = 4;
	 			my $tmpStr2 = "g";
	 			$nucles{$tmpStr2} = 4;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "c";
	 				$nucles{$tmpStr1} = $curTemp + 4;
	 				my $tmpStr2 = $str . "g";
	 				$nucles{$tmpStr2} = $curTemp + 4; 			
	 			} 		
	 		  }
	 		}	 	
	 	}
	 	elsif ($adenine eq "W") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "a";
	 			$nucles{$tmpStr1} = 2;
	 			my $tmpStr2 = "t";
	 			$nucles{$tmpStr2} = 2;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;
	 				my $tmpStr2 = $str . "t";
	 				$nucles{$tmpStr2} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}
	 	
	 	}
	 	elsif ($adenine eq "M") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "a";
	 			$nucles{$tmpStr1} = 2;
	 			my $tmpStr2 = "c";
	 			$nucles{$tmpStr2} = 4;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;
	 				my $tmpStr2 = $str . "c";
	 				$nucles{$tmpStr2} = $curTemp + 4; 			
	 			} 		
	 		  }
	 		}
	 	
	 	}
	 	elsif ($adenine eq "K") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "g";
	 			$nucles{$tmpStr1} = 4;
	 			my $tmpStr2 = "t";
	 			$nucles{$tmpStr2} = 2;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "g";
	 				$nucles{$tmpStr1} = $curTemp + 4;
	 				my $tmpStr2 = $str . "t";
	 				$nucles{$tmpStr2} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}
	 	
	 	}
	 	elsif ($adenine eq "B") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "g";
	 			$nucles{$tmpStr1} = 4;
	 			my $tmpStr2 = "c";
	 			$nucles{$tmpStr2} = 4;
	 			my $tmpStr3 = "t";
	 				$nucles{$tmpStr3} = 2;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "g";
	 				$nucles{$tmpStr1} = $curTemp + 4;
	 				my $tmpStr2 = $str . "c";
	 				$nucles{$tmpStr2} = $curTemp + 4;
	 				my $tmpStr3 = $str . "t";
	 				$nucles{$tmpStr3} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}	 	
	 	}
	 	elsif ($adenine eq "D") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "a";
	 			$nucles{$tmpStr1} = 2;
	 			my $tmpStr2 = "g";
	 			$nucles{$tmpStr2} = 4;
	 			my $tmpStr3 = "t";
	 		    $nucles{$tmpStr3} = 2;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;
	 				my $tmpStr2 = $str . "g";
	 				$nucles{$tmpStr2} = $curTemp + 4;
	 				my $tmpStr3 = $str . "t";
	 				$nucles{$tmpStr3} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}	 	
	 	}
	 	elsif ($adenine eq "H") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "a";
	 			$nucles{$tmpStr1} = 2;
	 			my $tmpStr2 = "c";
	 			$nucles{$tmpStr2} = 4;
	 			my $tmpStr3 = "t";
	 			$nucles{$tmpStr3} = 2;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;
	 				my $tmpStr2 = $str . "c";
	 				$nucles{$tmpStr2} = $curTemp + 4;
	 				my $tmpStr3 = $str . "t";
	 				$nucles{$tmpStr3} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}	 	
	 	}
	 	elsif ($adenine eq "V") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "a";
	 			$nucles{$tmpStr1} = 2;
	 			my $tmpStr2 = "c";
	 			$nucles{$tmpStr2} = 4;
	 			my $tmpStr3 = "g";
	 				$nucles{$tmpStr3} = 4;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;
	 				my $tmpStr2 = $str . "c";
	 				$nucles{$tmpStr2} = $curTemp + 4;
	 				my $tmpStr3 = $str . "g";
	 				$nucles{$tmpStr3} = $curTemp + 4; 			
	 			} 		
	 		  }
	 		}	 	
	 	}
	 	elsif ($adenine eq "N") {
	 		$wildCard++;
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "a";
	 			$nucles{$tmpStr1} = 2;
	 			my $tmpStr2 = "c";
	 			$nucles{$tmpStr2} = 4;
	 			my $tmpStr3 = "g";
	 			$nucles{$tmpStr3} = 4;
	 			my $tmpStr4 = "t";
	 			$nucles{$tmpStr4} = 2;
	 		} else {	
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;
	 				my $tmpStr2 = $str . "c";
	 				$nucles{$tmpStr2} = $curTemp + 4;
	 				my $tmpStr3 = $str . "g";
	 				$nucles{$tmpStr3} = $curTemp + 4;
	 				my $tmpStr4 = $str . "t";
	 				$nucles{$tmpStr4} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}	 	
	 	} 
	 	elsif ($adenine eq "a") {
	 		if ($charIndex == 1) {
	 			#print OUTP "all keys length:" . length($str) . "\n";
	 			my $tmpStr1 = "a";
	 		    $nucles{$tmpStr1} = 2;
	 		   
	 		} else {
	 			foreach my $str (keys %nucles) {
	 			
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "a";
	 				$nucles{$tmpStr1} = $curTemp + 2;	 						
	 			} 		
	 		   } 		
	 		}	 			
	 	}
	 	elsif ($adenine eq "t") {
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "t";
	 		    $nucles{$tmpStr1} = 2;
	 		} else {
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "t";
	 				$nucles{$tmpStr1} = $curTemp + 2; 			
	 			} 		
	 		  }
	 		}
	 	}
	 	elsif ($adenine eq "g") {
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "g";
	 		    $nucles{$tmpStr1} = 4;
	 		} else {
	 		  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};
	 				my $tmpStr1 = $str . "g";
	 				$nucles{$tmpStr1} = $curTemp + 4;	 							
	 			} 		
	 		  }
	 		}	 	
	 	}
	 	elsif ($adenine eq "c") {
	 		if ($charIndex == 1) {
	 			my $tmpStr1 = "c";
	 		    $nucles{$tmpStr1} = 4;
	 		} else {
	 	 	  foreach my $str (keys %nucles) {
	 			if (length($str) == ($charIndex - 1)) {
	 				my $curTemp = $nucles{$str};	 				
	 				my $tmpStr1 = $str . "c";
	 				$nucles{$tmpStr1} = $curTemp + 4; 				 			
	 			} 		
	 		  }
	 		}	 	
	 	}	 
	 }
	 
	 # if wild-car is over th MAX or all input polymers are over 20 chars, then do next one
	 if ($wildCard > 4 || $curPolySize > 20) {
	    	print OUTP "Error in\"", $curPoly, "\"  ";
	    	next;    
	 } 
	 # For the case with multiple polymers
	 elsif ($wildCard > 0) { 
	    	my $lowerTemp = 100;
	    	my $upperTemp = 0;
	    	my $curTemp = 0;
	    	foreach my $str (keys %nucles) {	 			
	 			if (length($str) == $curPolySize) {
	 				
	 				$curTemp = $nucles{$str};	 				
	 				if ($curTemp < $lowerTemp) {
	 					$lowerTemp = $curTemp;
	 				} 
	 				if ($curTemp > $upperTemp) {
	 					$upperTemp = $curTemp;
	 				} 			 			
	 			}
	 		}	    	
	    	if ($lowerTemp == $upperTemp) {
	    	   printf OUTP "%s  %.2f  ", $curPoly,$lowerTemp;	    	   
	    	} else {
	    	   printf OUTP "%s  %.2f--%.2f  \n", $curPoly,$lowerTemp,$upperTemp;
	    	}	    	
	    	@sorted = sort { $nucles{$a} cmp $nucles{$b} } keys %nucles; 
	    	
	    	foreach my $str (@sorted) {	    		
	 			if (length($str) == $curPolySize) {
	 				printf OUTP "%s  %.2f  ", $str,$nucles{$str};  			
	 			} 		
	 		}
	    
	    } 
	    # For the case without multiple polymers
	    else {	    	    	
	    	foreach my $str (keys %nucles) {
	    		if (length($str) == $curPolySize) {
	 				printf OUTP "%s  %.2f  ", $str,$nucles{$str};		
	 			} 		
	 		}	    
	    }
}