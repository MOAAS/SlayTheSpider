package SlayTheSpider.Model.Game.StatusEffect;

import java.util.ArrayList;
import java.util.List;

public class StatusEffectList implements StatusEffectGroup {
    private List<StatusEffectListEntry> statusEffects = new ArrayList<>();

    @Override
    public void add(StatusEffect type, int duration) {
        for (StatusEffectListEntry effect : statusEffects) {
            if (effect.getEffect().getName().equals(type.getName())) {
                effect.increaseDuration(duration);
                return;
            }
        }
        this.statusEffects.add(new StatusEffectListEntry(type, duration));
    }

    @Override
    public void endTurn() {
        for (int i = 0; i < statusEffects.size(); ) {
            statusEffects.get(i).endRound();
            if (statusEffects.get(i).isWornOff())
                statusEffects.remove(i);
            else i++;
        }
    }

    @Override
    public List<StatusEffectListEntry> getList() {
        return statusEffects;
    }

    @Override
    public List<StatusEffect> getEffects() {
        List<StatusEffect> effects = new ArrayList<>();
        for (StatusEffectListEntry effect : statusEffects)
            effects.add(effect.getEffect());
        return effects;
    }

    @Override
    public void clear() {
        this.statusEffects.clear();
    }

    @Override
    public int size() {
        return this.statusEffects.size();
    }
}
