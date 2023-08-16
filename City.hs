module City (City(), newC,nameC,distanceC) where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC str point = Cit str point

nameC :: City -> String
nameC (Cit str point) = str

distanceC :: City -> City -> Float
distanceC (Cit str point) (Cit str' point') = difP (point) (point')

bsas = newC "Buenos Aires" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)
