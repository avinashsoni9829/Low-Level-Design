package org.example.snake_ladder;

import java.util.List;

public interface BoardGeneratorFeatures {
    public  void initialize();
    public  void ask(Boolean flag);
    public void askSize(List<EntityType> size);
    public void createCellInstance(Boolean flag);
    public void  performChoice(int choice , Boolean flag);
    public void generateInstances(Boolean flag);
}
