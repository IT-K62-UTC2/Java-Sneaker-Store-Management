package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.models.Position;
import utc2.itk62.sneaker.repositories.PositionRepo;
import utc2.itk62.sneaker.repositories.StaffRepo;

import java.util.List;

public class PositionServie {
    private static final PositionRepo positionRepo = new PositionRepo();

    public PositionServie() {
    }

    public List<Position> getAllPosition(){
        return positionRepo.getAllPosition();
    }
}
