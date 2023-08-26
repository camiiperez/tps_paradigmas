module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

difP :: Point -> Point -> Float 
difP (Poi x y) (Poi x' y') = sqrt((fromIntegral(x-x'))**2 + (fromIntegral(y-y'))** 2)
