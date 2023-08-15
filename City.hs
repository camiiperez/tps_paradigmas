module City (City(Cit), newC,nameC) where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC str point = Cit str point

nameC :: City -> String
nameC (Cit str point) = str

distanceC :: City -> City -> Float
distanceC (Cit (str) (Poi x y)) (Cit (str') (Poi x' y')) = difP (Poi x y) (Poi x' y')

bsas = newC "Buenos Aires" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)