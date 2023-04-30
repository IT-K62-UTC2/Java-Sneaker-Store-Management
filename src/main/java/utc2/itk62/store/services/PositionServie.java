package utc2.itk62.store.services;

import utc2.itk62.store.models.Position;
import utc2.itk62.store.repositories.PositionRepo;

import java.util.List;

public class PositionServie {
    private static final PositionRepo positionRepo = new PositionRepo();

    public PositionServie() {
    }

    public List<Position> getAllPosition(){
        return positionRepo.getAllPosition();
    }
}
