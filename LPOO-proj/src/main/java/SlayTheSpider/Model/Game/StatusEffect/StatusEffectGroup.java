package SlayTheSpider.Model.Game.StatusEffect;

import java.util.List;

public interface StatusEffectGroup {
    void add(StatusEffect type, int duration);

    void endTurn();

    List<StatusEffectListEntry> getList();

    List<StatusEffect> getEffects();

    void clear();

    int size();
}
